# local execution
echo "Executing the ap0 node."
cd /home/marco/Desktop/MuseumHeist/dirAP0
java -cp "../genclass.jar:." serverSide.main.ServerAssaultParty 22313 127.0.0.1 22312
echo "AP0 Server shutdown."



# global execution
#echo "Transfering data to the ap0 node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
#sshpass -f password scp dirAP0.zip sd202@l040101-ws07.ua.pt:test/MuseumHeist
#echo "Decompressing data sent to the AP0 node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAP0.zip'
#sshpass -f password scp genclass.zip sd202@l040101-ws07.ua.pt:test/MuseumHeist/dirAP0
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirAP0 ; unzip -uq genclass.zip'
#echo "Executing program at the AP0 node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirAP0 ; java serverSide.main.ServerAssaultParty 22317 l040101-ws01.ua.pt 22312'
