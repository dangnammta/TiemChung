DEPLOY_FOLDER="../deployVC/VaccinationRegistration"

cp ./*/bin/* $DEPLOY_FOLDER/
cp ./*/*.properties $DEPLOY_FOLDER/

cp ./runVR.sh $DEPLOY_FOLDER
cp ./killVR.sh $DEPLOY_FOLDER
cp ./changeNameVR.sh $DEPLOY_FOLDER
