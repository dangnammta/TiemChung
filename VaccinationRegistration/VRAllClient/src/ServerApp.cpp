/* 
 * File:   ServerApp.cpp
 * Author: longmd
 * 
 * Created on February 23, 2012, 6:18 PM
 */

#include "ServerApp.h"

using namespace std;

#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <exception>
#include <bits/stl_bvector.h>
#include <map>
#include <iomanip>
#include <float.h>
#include "Poco/StringTokenizer.h"
#include "Poco/Util/HelpFormatter.h"
#include "Poco/NumberParser.h"
#include "Poco/String.h"
#include "TClientTypes.h"
#include "Configuration.h"
#include <stdlib.h>

using namespace CRDHN::VR::Data;
using namespace CRDHN::VR::Response;

ServerApp::ServerApp() : m_showHelp(false) {
    m_servicePort = 11771;
    m_serviceCfgPort = 11772;
    m_host = "127.0.0.1";
}

ServerApp::ServerApp(const ServerApp& orig) {
    m_showHelp = orig.m_showHelp;
    m_servicePort = orig.m_servicePort;
    m_serviceCfgPort = orig.m_serviceCfgPort;
    m_host = orig.m_host;
}

ServerApp::~ServerApp(void) {
}

void ServerApp::initialize(Poco::Util::Application& app) {
    if (m_showHelp)
        return;
    Poco::Util::ServerApplication::initialize(app);
}

int ServerApp::main(const vector<string>& args) {
    return Poco::Util::ServerApplication::main(args);
}

void ServerApp::defineOptions(Poco::Util::OptionSet& options) {
    loadConfiguration();

    Configuration::_VRRegistrationManagerServicePort = this->config().getInt(Configuration::_configVRRegistrationManagerServicePort, Configuration::_VRRegistrationManagerServicePort);
    Configuration::_VRRegistrationManagerServiceHost = this->config().getString(Configuration::_configVRRegistrationManagerServiceHost, Configuration::_VRRegistrationManagerServiceHost);
    Configuration::_VREventManagerServicePort = this->config().getInt(Configuration::_configVREventManagerServicePort, Configuration::_VREventManagerServicePort);
    Configuration::_VREventManagerServiceHost = this->config().getString(Configuration::_configVREventManagerServiceHost, Configuration::_VREventManagerServiceHost);
    Configuration::_VRCentreInfoStoreServicePort = this->config().getInt(Configuration::_configVRCentreInfoStoreServicePort, Configuration::_VRCentreInfoStoreServicePort);
    Configuration::_VRCentreInfoStoreServiceHost = this->config().getString(Configuration::_configVRCentreInfoStoreServiceHost, Configuration::_VRCentreInfoStoreServiceHost);

    Poco::Util::ServerApplication::defineOptions(options);

    options.addOption(Poco::Util::Option("help", "h")
            .description("Show help messages")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::handleShowHelp)));

    //	options.addOption(Poco::Util::Option("addCentreInfo", "aci")
    //			.description("addCentreInfo")
    //			.callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::addCentreInfo))
    //			);

    options.addOption(Poco::Util::Option("getEventStatus", "ges")
            .argument("<eventId>", true)
            .description("getEventStatus")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::getEventStatus))
            );
    options.addOption(Poco::Util::Option("addRegistration", "ar")
            .description("addRegistration")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::addRegistration))
            );
    options.addOption(Poco::Util::Option("addExtraRegistration", "aer")
            .description("addExtraRegistration")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::addExtraRegistration))
            );
    options.addOption(Poco::Util::Option("addExtendedRegistration", "aexr")
            .description("addExtendedRegistration")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::addExtendedRegistration))
            );

    options.addOption(Poco::Util::Option("createEvent", "ce")
            .description("createEvent")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::createEvent))
            );

    options.addOption(Poco::Util::Option("updateEventInfo", "uei")
            .argument("<eventId>", true)
            .description("updateEventInfo")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::updateEventInfo))
            );
    options.addOption(Poco::Util::Option("getEventInfo", "gei")
            .argument("<eventId>", true)
            .description("getEventInfo")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::getEventInfo))
            );
    options.addOption(Poco::Util::Option("removeEvent", "re")
            .argument("<eventId>", true)
            .description("removeEvent")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::removeEvent))
            );

    options.addOption(Poco::Util::Option("getRegistrations", "grs")
            .argument("<eventId>", true)
            .description("getRegistrations")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::getRegistrations))
            );

    options.addOption(Poco::Util::Option("getEvents", "gves")
            .description("getEvents")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::getEvents))
            );
    options.addOption(Poco::Util::Option("updateInjectionStatus", "uis")
            .argument("<registationId>:<injectionStatus>", true)
            .description("updateInjectionStatus")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::updateInjectionStatus))
            );

    options.addOption(Poco::Util::Option("updateRegistration", "ur")
            .description("updateRegistration")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::updateRegistration))
            );

    options.addOption(Poco::Util::Option("removeRegistration", "rr")
            .description("removeRegistration")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::removeRegistration))
            );

    options.addOption(Poco::Util::Option("createCentre", "cc")
            .description("createCentre")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::createCentre))
            );

    options.addOption(Poco::Util::Option("getCentreInfo", "gci")
            .description("getCentreInfo")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::getCentreInfo))
            );

    options.addOption(Poco::Util::Option("updateCentreInfo", "uci")
            .description("updateCentreInfo")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::updateCentreInfo))
            );

    options.addOption(Poco::Util::Option("removeCentre", "rm")
            .description("removeCentre")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::removeCentre))
            );

    options.addOption(Poco::Util::Option("getCentres", "gc")
            .description("getCentres")
            .callback(Poco::Util::OptionCallback<ServerApp > (this, &ServerApp::getCentres))
            );
}

void ServerApp::handleShowHelp(const string& name, const string& value) {
    m_showHelp = true;
    Poco::Util::HelpFormatter helpFormatter(options());
    helpFormatter.setCommand(commandName());

    helpFormatter.setUsage("OPTIONS");
    helpFormatter.setHeader("Parameters:");
    helpFormatter.format(cout);
}

//CentreInfo
/*
void ServerApp::addCentreInfo(const std::string& name, const std::string& value){
        cout << "addCentreInfo: " << value << endl;
        int64_t startTime = Poco::Timestamp().epochMicroseconds();

        VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
        if (!client->sureOpen()) {
                cout << "[ERROR] Connection to VRCentreInfoStoreService[" << Configuration::_VRCentreInfoStoreServiceHost << ":" << Configuration::_VRCentreInfoStoreServicePort << "] is NOT opened!" << endl;
        }
        else {
                try {
                        TCentreInfo centreInfo;
                        centreInfo.name = "Bệnh Viện Nhi Trung Ương";
                        TCID centreId = client->add(centreInfo);
                        cout << "centreId= " << centreId << endl;
                }
                catch (exception& ex) {
                        cout << "EXCEPTION: " << ex.what() << endl;
                }
        }

        int64_t endTime = Poco::Timestamp().epochMicroseconds();
        cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
} */

// RegistrationManager
void ServerApp::getEventStatus(const std::string& name, const std::string& value){
    cout << "getEventStatus: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRRegistrationManagerServiceClientT client = Configuration::getVRRegistrationManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRRegistrationManagerService[" << Configuration::_VRRegistrationManagerServiceHost << ":" << Configuration::_VRRegistrationManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TEID eventId = Poco::NumberParser::parse(value);
            TError response;
            client->getEventStatus(response, eventId);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::addRegistration(const std::string& name, const std::string& value){
    cout << "addRegistration: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRRegistrationManagerServiceClientT client = Configuration::getVRRegistrationManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRRegistrationManagerService[" << Configuration::_VRRegistrationManagerServiceHost << ":" << Configuration::_VRRegistrationManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TRegistrationInfo registrationInfo;
            registrationInfo.registrationType = TRT_ONLINE;
            registrationInfo.eventId = 1;
            registrationInfo.childrenName = "Nguyễn Văn Con";
            registrationInfo.childrenDateOfBirth = Poco::Timestamp().epochTime() - 365 * 24 * 60 * 60;
            registrationInfo.childrenCarrierName = "Nguyễn Văn Bố";
            srand(time(NULL));
            stringstream code;
            code << rand() % 1000000000 + 1;

            registrationInfo.childrenCarrierPIN = code.str();
            registrationInfo.childrenCarrierPhoneNumber = "01234567890";
            registrationInfo.childrenCarrierEmail = "nguyenvanbo@gmail.com";
            registrationInfo.childrenCarrierAddress = "112 Nguyen Tuan";

            TRMAddRegistrationResponse response;
            client->addRegistration(response, registrationInfo);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "registrationCode = " << response.registrationCode << endl;
            cout << "injectionOrderNumber = " << response.injectionOrderNumber << endl;
            cout << "injectionDate = " << response.injectionDate << endl;
            cout << "injectionTime = " << response.injectionTime << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::addExtraRegistration(const std::string& name, const std::string& value){
    cout << "addExtraRegistration: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRRegistrationManagerServiceClientT client = Configuration::getVRRegistrationManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRRegistrationManagerService[" << Configuration::_VRRegistrationManagerServiceHost << ":" << Configuration::_VRRegistrationManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TRegistrationInfo registrationInfo;
            registrationInfo.registrationType = TRT_ONLINE;
            registrationInfo.eventId = 1;
            registrationInfo.childrenName = "Nguyễn Văn Con EXTRA";
            registrationInfo.childrenDateOfBirth = Poco::Timestamp().epochTime() - 365 * 24 * 60 * 60;
            registrationInfo.childrenCarrierName = "Nguyễn Văn Bố";
            srand(time(NULL));
            stringstream code;
            code << rand() % 1000000000 + 1;

            registrationInfo.childrenCarrierPIN = code.str();
            registrationInfo.childrenCarrierPhoneNumber = "01234567890";
            registrationInfo.childrenCarrierEmail = "nguyenvanbo@gmail.com";
            registrationInfo.childrenCarrierAddress = "112 Nguyen Tuan";
            registrationInfo.injectionTime = "9h sáng mai";

            TRMAddExtraRegistrationResponse response;
            client->addExtraRegistration(response, registrationInfo);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "registrationCode = " << response.registrationCode << endl;
            cout << "injectionOrderNumber = " << response.injectionOrderNumber << endl;
            cout << "injectionDate = " << response.injectionDate << endl;
            cout << "injectionTime = " << response.injectionTime << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::addExtendedRegistration(const std::string& name, const std::string& value){
    cout << "addRegistration: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRRegistrationManagerServiceClientT client = Configuration::getVRRegistrationManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRRegistrationManagerService[" << Configuration::_VRRegistrationManagerServiceHost << ":" << Configuration::_VRRegistrationManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TRegistrationInfo registrationInfo;
            registrationInfo.registrationType = TRT_ONLINE;
            registrationInfo.eventId = 1;
            registrationInfo.childrenName = "Nguyễn Cảnh Anh";
            registrationInfo.childrenDateOfBirth = 1398048983;
            registrationInfo.childrenCarrierName = "Nguyễn Thị Kháng";
            //			srand (time(NULL));
            //			stringstream code;
            //			code<< rand() % 1000000000 + 1;
            //			
            registrationInfo.childrenCarrierPIN = "011488834";
            registrationInfo.childrenCarrierPhoneNumber = "";
            registrationInfo.childrenCarrierEmail = "";
            registrationInfo.childrenCarrierAddress = "nhà số 16,ngách 29/68 phố Khương Hạ, T.Xuân,HN";

            TRMAddRegistrationResponse response;
            client->addExtendedRegistration(response, registrationInfo, 10);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "registrationCode = " << response.registrationCode << endl;
            cout << "injectionOrderNumber = " << response.injectionOrderNumber << endl;
            cout << "injectionDate = " << response.injectionDate << endl;
            cout << "injectionTime = " << response.injectionTime << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

// EventManager
void ServerApp::createEvent(const std::string& name, const std::string& value){
    cout << "createEvent: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TEventInfo eventInfo;
            eventInfo.eventName = "Đợt tiêm chủng Pentaxim 8/1/2016";
            eventInfo.startTimeRegistration = 1452161432;
            eventInfo.startTimeInjection = 1452218400;
            eventInfo.totalOfDoses = 210;
            eventInfo.numberOfInjectionsPerDay = 6;
            eventInfo.vaccineId = 1;
            eventInfo.centreId = 1;
            eventInfo.workingShiftNames.clear();
            eventInfo.workingShiftNames.push_back("8h-9h");
            eventInfo.workingShiftNames.push_back("9h-10h");
            eventInfo.workingShiftNames.push_back("10h-11h");
            eventInfo.workingShiftNames.push_back("13h30-14h30");
            eventInfo.workingShiftNames.push_back("14h30-16h");

            TVMCreateEventResponse response;
            client->createEvent(response, eventInfo);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "eventId = " << response.eventId << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::updateEventInfo(const std::string& name, const std::string& value){

    cout << "updateEventInfo: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TEID eventId = Poco::NumberParser::parse(value);
            TEventInfo eventInfo;
            eventInfo.eventName = "Đợt tiêm chủng Pentaxim 9/2/2016";
            eventInfo.startTimeRegistration = 1452161432;
            eventInfo.startTimeInjection = 1452218400;
            eventInfo.totalOfDoses = 210;
            eventInfo.numberOfInjectionsPerDay = 6;
            eventInfo.vaccineId = 1;
            eventInfo.centreId = 1;
            eventInfo.workingShiftNames.clear();
            eventInfo.workingShiftNames.push_back("8h-9h");
            eventInfo.workingShiftNames.push_back("9h-10h");
            eventInfo.workingShiftNames.push_back("10h-11h");
            eventInfo.workingShiftNames.push_back("13h30-14h30");
            eventInfo.workingShiftNames.push_back("14h30-16h");

            TError response;
            client->updateEventInfo(response, eventId, eventInfo);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::getEventInfo(const std::string& name, const std::string& value){
    cout << "getEventInfo: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TEID eventId = Poco::NumberParser::parse(value);
            TVMGetEventInfoResponse response;
            client->getEventInfo(response, eventId);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "eventId = " << response.eventInfo.eventId << endl;
            cout << "eventName = " << response.eventInfo.eventName << endl;
            cout << "eventStatus = " << response.eventInfo.eventStatus << endl;
            cout << "startTimeRegistration = " << response.eventInfo.startTimeRegistration << endl;
            cout << "startTimeInjection = " << response.eventInfo.startTimeInjection << endl;
            cout << "totalOfDoses = " << response.eventInfo.totalOfDoses << endl;
            cout << "numberOfInjectionsPerDay = " << response.eventInfo.numberOfInjectionsPerDay << endl;
            cout << "numberOfRegistrations = " << response.eventInfo.numberOfRegistrations << endl;
            cout << "numberOfVaccinatedChildren = " << response.eventInfo.numberOfVaccinatedChildren << endl;
            cout << "vaccineId = " << response.eventInfo.vaccineId << endl;
            cout << "centreId = " << response.eventInfo.centreId << endl;
            cout << "endTimeRegistration = " << response.eventInfo.endTimeRegistration << endl;
            cout << "workingShiftNames = " << response.eventInfo.workingShiftNames.size() << endl;

            for (int i=0; i<response.eventInfo.workingShiftNames.size(); i++){
                cout << "	" << response.eventInfo.workingShiftNames[i];
            }
            cout << endl;

            cout << "registrations = " << response.eventInfo.registrations.size() << endl;
            for (int i=0; i<response.eventInfo.registrations.size(); i++){
                cout << "	" << response.eventInfo.registrations[i];
            }
            cout << endl;

            cout << "extendedRegistrations = " << response.eventInfo.extendedRegistrations.size() << endl;
            for (int i=0; i<response.eventInfo.extendedRegistrations.size(); i++){
                cout << "	" << response.eventInfo.extendedRegistrations[i];
            }
            cout << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::removeEvent(const std::string& name, const std::string& value){

    cout << "removeEvent: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } else {
        try {
            TEID eventId = Poco::NumberParser::parse(value);
            TError response;
            client->removeEvent(response, eventId);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;
        } catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }
    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::getRegistrations(const std::string& name, const std::string& value){
    cout << "getRegistrations: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TEID eventId = Poco::NumberParser::parse(value);
            TVMGetRegistrationsResponse response;
            client->getRegistrations(response, eventId);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            for (int i=0; i<response.registrations.size(); i++){
                cout << "Registration " << i << " :" << endl;
                TRegistrationInfo registrationInfo = response.registrations[i];
                cout << "	registrationId = " << registrationInfo.registrationId << endl;
                cout << "	registrationType = " << registrationInfo.registrationType << endl;
                cout << "	registrationTime = " << registrationInfo.registrationTime << endl;
                cout << "	eventId = " << registrationInfo.eventId << endl;
                cout << "	childrenName = " << registrationInfo.childrenName << endl;
                cout << "	childrenDateOfBirth = " << registrationInfo.childrenDateOfBirth << endl;
                cout << "	childrenCarrierName = " << registrationInfo.childrenCarrierName << endl;
                cout << "	childrenCarrierPIN = " << registrationInfo.childrenCarrierPIN << endl;
                cout << "	childrenCarrierPhoneNumber = " << registrationInfo.childrenCarrierPhoneNumber << endl;
                cout << "	childrenCarrierEmail = " << registrationInfo.childrenCarrierEmail << endl;
                cout << "	childrenCarrierAddress = " << registrationInfo.childrenCarrierAddress << endl;
                cout << "	injectionStatus = " << registrationInfo.injectionStatus << endl;
                cout << "	registrationCode = " << registrationInfo.registrationCode << endl;
                cout << "	injectionOrderNumber = " << registrationInfo.injectionOrderNumber << endl;
                cout << "	injectionTime = " << registrationInfo.injectionTime << endl;
                cout << "	injectionDate = " << registrationInfo.injectionDate << endl;
                cout << "	injectionRealTime = " << registrationInfo.injectionRealTime << endl;
            }
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::getEvents(const std::string& name, const std::string& value){
    cout << "getEvents: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TVMGetEventsResponse response;
            client->getEvents(response, 1);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            for (int i=0; i<response.events.size(); i++){
                cout << "Event " << i << " :" << endl;
                TEventBasicInfo eventInfo = response.events[i];
                cout << "	eventId = " << eventInfo.eventId << endl;
                cout << "	eventName = " << eventInfo.eventName << endl;
                cout << "	eventStatus = " << eventInfo.eventStatus << endl;
                cout << "	startTimeRegistration = " << eventInfo.startTimeRegistration << endl;
                cout << "	vaccineId = " << eventInfo.vaccineId << endl;
                cout << "	vaccineName = " << eventInfo.vaccineName << endl;
            }
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::updateInjectionStatus(const std::string& name, const std::string& value){
    cout << "updateInjectionStatus: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            Poco::StringTokenizer token(value, ":");
            TRID registrationId = Poco::NumberParser::parse(token[0]);
            int32_t injectionStatus = Poco::NumberParser::parse(token[1]);
            TError response;
            client->updateInjectionStatus(response, registrationId, injectionStatus);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::updateRegistration(const std::string& name, const std::string& value){
    cout << "updateRegistration: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TRegistrationInfo registrationInfo;
            registrationInfo.registrationId = 5;
            registrationInfo.registrationType = 1;
            registrationInfo.registrationTime = 1452218400;
            registrationInfo.eventId = 1;
            registrationInfo.childrenName = "Nguyễn Cảnh Anh";
            registrationInfo.childrenDateOfBirth = 1452218451;
            registrationInfo.childrenCarrierName = "Bùi Kiều Ngọc";
            registrationInfo.childrenCarrierPIN = "012390006";
            registrationInfo.childrenCarrierPhoneNumber = "";
            registrationInfo.childrenCarrierEmail = "";
            registrationInfo.childrenCarrierAddress = "nhà số 16,ngách 29/68 phố Khương Hạ, T.Xuân,HN";
            registrationInfo.injectionStatus = 0;
            registrationInfo.registrationCode = "YXJMHUSZ";
            registrationInfo.injectionOrderNumber = 0;
            registrationInfo.injectionTime = "Từ 13h30 – 14h30, ngày 13/1/2016";
            registrationInfo.injectionDate = 1455863559;
            registrationInfo.injectionRealTime = -1;

            TError response;
            client->updateRegistration(response, registrationInfo.registrationId, registrationInfo);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::removeRegistration(const std::string& name, const std::string& value) {
    cout << "removeRegistration: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VREventManagerServiceClientT client = Configuration::getVREventManagerServiceClient();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VREventManagerService[" << Configuration::_VREventManagerServiceHost << ":" << Configuration::_VREventManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TRegistrationInfo registrationInfo;
            registrationInfo.registrationId = 5;
            TError response;
            client->removeRegistration(response, registrationInfo.registrationId);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

//Centremanager

void ServerApp::createCentre(const std::string& name, const std::string& value) {
    cout << "createCentre: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRCentreManagerServiceClientT client = Configuration::getVRCentreManagerServiceClientT();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRCentreManagerServiceClient[" << Configuration::_VRCentreManagerServiceHost << ":" << Configuration::_VRCentreManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TCentreInfo centreInfo;
            centreInfo.address = "18/879 La Thành – Đống Đa – Hà Nội";
            centreInfo.name = "Bệnh Viện Nhi Trung Ương";
            centreInfo.phoneNumber = "04 6 273 8573";

            TCMCreateCentreResponse response;
            client->createCentre(response, centreInfo);
            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "centreId = " << response.centreId << endl;
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }
    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::getCentreInfo(const std::string& name, const std::string& value) {
    cout << "getCentreInfo: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRCentreManagerServiceClientT client = Configuration::getVRCentreManagerServiceClientT();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRCentreManagerServiceClient[" << Configuration::_VRCentreManagerServiceHost << ":" << Configuration::_VRCentreManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TCID centreId = Poco::NumberParser::parse(value);
            TCMGetCentreInfoResponse response;
            client->getCentreInfo(response, centreId);

            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            cout << "centreId = " << response.centreInfo.centreId << endl;
            cout << "name = " << response.centreInfo.name << endl;
            cout << "address = " << response.centreInfo.address << endl;
            cout << "phoneNumber = " << response.centreInfo.phoneNumber << endl;
            cout << "eventIds: " << endl;
            for (int i = 0; i < response.centreInfo.eventIds.size(); i++) {
                cout << "	" << response.centreInfo.eventIds[i];
            }
            cout << endl;

        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::updateCentreInfo(const std::string& name, const std::string& value) {
    cout << "updateCentreInfo: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRCentreManagerServiceClientT client = Configuration::getVRCentreManagerServiceClientT();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRCentreManagerServiceClient[" << Configuration::_VRCentreManagerServiceHost << ":" << Configuration::_VRCentreManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TCID centreId = Poco::NumberParser::parse(value);
            TCentreInfo centreInfo;
            centreInfo.address = "18/879 La Thành – Đống Đa – Hà Nội";
            centreInfo.name = "Bệnh Viện Nhi Trung Ương";
            centreInfo.phoneNumber = "04 6 273 8573";

            TError response;
            client->updateCentreInfo(response, centreId, centreInfo);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;

        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::removeCentre(const std::string& name, const std::string& value) {
    cout << "removeCentre: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRCentreManagerServiceClientT client = Configuration::getVRCentreManagerServiceClientT();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRCentreManagerServiceClient[" << Configuration::_VRCentreManagerServiceHost << ":" << Configuration::_VRCentreManagerServicePort << "] is NOT opened!" << endl;
    } else {
        try {
            TCID centreId = Poco::NumberParser::parse(value);
            TError response;
            client->removeCentre(response, centreId);
            cout << "error code = " << response.errorCode << endl;
            cout << "error message = " << response.errorMessage << endl;

        } catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }

    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}

void ServerApp::getCentres(const std::string& name, const std::string& value) {
    cout << "getCentres: " << value << endl;
    int64_t startTime = Poco::Timestamp().epochMicroseconds();

    VRCentreManagerServiceClientT client = Configuration::getVRCentreManagerServiceClientT();
    if (!client->sureOpen()) {
        cout << "[ERROR] Connection to VRCentreManagerServiceClient[" << Configuration::_VRCentreManagerServiceHost << ":" << Configuration::_VRCentreManagerServicePort << "] is NOT opened!" << endl;
    } 
    else {
        try {
            TCMGetCentresResponse response;
            client->getCentres(response);

            cout << "error code = " << response.error.errorCode << endl;
            cout << "error message = " << response.error.errorMessage << endl;
            for (int i = 0; i < response.centres.size(); i++) {
                cout << "Centre " << i << " :" << endl;
                TCentreInfo centreInfo = response.centres[i];
                cout << "	centreId = " << centreInfo.centreId << endl;
                cout << "	name = " << centreInfo.name << endl;
                cout << "	address = " << centreInfo.address << endl;
                cout << "	phoneNumber = " << centreInfo.phoneNumber << endl;
            }
        } 
        catch (exception& ex) {
            cout << "EXCEPTION: " << ex.what() << endl;
        }
    }
    int64_t endTime = Poco::Timestamp().epochMicroseconds();
    cout << "Running time: " << (endTime - startTime) << " micro-second" << endl;
}