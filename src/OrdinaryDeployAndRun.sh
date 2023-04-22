# local execution
echo "Executing the ordinary node."
cd /home/vm/HeistMuseum/dirOrdinary
java -cp "../genclass.jar:." clientSide.main.ClientOrdinary 22317 127.0.0.1 22312
echo "ordinary Server shutdown."

# global execution
#echo "Transfering data to the ordinary node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
#sshpass -f password scp dirOrdinary.zip sd202@l040101-ws07.ua.pt:test/MuseumHeist
#echo "Decompressing data sent to the Ordinary node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirOrdinary.zip'
#sshpass -f password scp genclass.zip sd202@l040101-ws07.ua.pt:test/MuseumHeist/dirOrdinary
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirOrdinary ; unzip -uq genclass.zip'
#echo "Executing program at the Ordinary node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirOrdinary ; java serverSide.main.ServerAssaultParty0 22317 l040101-ws01.ua.pt 22312'