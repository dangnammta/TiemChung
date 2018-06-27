FOLDER="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
killall -9 $FOLDER/vrregistrationmanagerservice
killall -9 $FOLDER/vreventmanagerservice
killall -9 $FOLDER/vrcentremanagerservice
killall -9 $FOLDER/vrdailyinjectionsstoreservice
killall -9 $FOLDER/vrdailyregistrationsstoreservice
killall -9 $FOLDER/vrregistrationinfostoreservice
killall -9 $FOLDER/vrregistrationcodesstoreservice
killall -9 $FOLDER/vrcentreinfostoreservice
killall -9 $FOLDER/vreventinfostoreservice
killall -9 $FOLDER/vrvaccineinfostoreservice
killall -9 $FOLDER/vrcommonstoreservice
killall -9 $FOLDER/vrstepcounterservice
killall -9 $FOLDER/flogservice
