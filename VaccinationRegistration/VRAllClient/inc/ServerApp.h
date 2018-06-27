/* 
 * File:   ServerApp.h
 * Author: longmd
 *
 * Created on February 23, 2012, 6:18 PM
 */

#ifndef SERVERAPP_H
#define SERVERAPP_H

#include <vector>
#include <iostream>
#include "Poco/Util/ServerApplication.h"

class ServerApp : public Poco::Util::ServerApplication {
public:
    ServerApp(void);
    ServerApp(const ServerApp& orig);
    virtual ~ServerApp(void);

    void initialize(Poco::Util::Application& app);
    virtual int main(const std::vector<std::string>& args);
    void defineOptions(Poco::Util::OptionSet& options);
    void handleShowHelp(const std::string& name, const std::string& value);

    //void addCentreInfo(const std::string& name, const std::string& value);
    // RegistrationManager
    void getEventStatus(const std::string& name, const std::string& value);
    void addRegistration(const std::string& name, const std::string& value);
    void addExtraRegistration(const std::string& name, const std::string& value);
    void addExtendedRegistration(const std::string& name, const std::string& value);

    // VaccinationManager
    void createEvent(const std::string& name, const std::string& value);
    void updateEventInfo(const std::string& name, const std::string& value);
    void getEventInfo(const std::string& name, const std::string& value);
    void removeEvent(const std::string& name, const std::string& value);
    void getRegistrations(const std::string& name, const std::string& value);
    void getEvents(const std::string& name, const std::string& value);
    void updateInjectionStatus(const std::string& name, const std::string& value);
    void updateRegistration(const std::string& name, const std::string& value);
    void removeRegistration(const std::string& name, const std::string& value);
    
    //Centremanager
    void createCentre(const std::string& name, const std::string& value);
    void getCentreInfo(const std::string& name, const std::string& value);
    void updateCentreInfo(const std::string& name, const std::string& value);
    void removeCentre(const std::string& name, const std::string& value);
    void getCentres(const std::string& name, const std::string& value);
private:
    bool m_showHelp;

    int m_servicePort;
    int m_serviceCfgPort;
    std::string m_host;
};

#endif /* SERVERAPP_H */