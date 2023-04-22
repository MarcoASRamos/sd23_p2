# local execution
echo "Executing the CS node."
cd /home/vm/HeistMuseum/dirCS
java -cp "../genclass.jar:." serverSide.main.ServerConcentrationSite 22317 127.0.0.1 22312
echo "CS Server shutdown."



# global execution
#echo "Transfering data to the CS node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
#sshpass -f password scp dirCS.zip sd202@l040101-ws07.ua.pt:test/MuseumHeist
#echo "Decompressing data sent to the CS node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirCS.zip'
#sshpass -f password scp genclass.zip sd202@l040101-ws07.ua.pt:test/MuseumHeist/dirCS
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirCS ; unzip -uq genclass.zip'
#echo "Executing program at the CS node."
#sshpass -f password ssh sd202@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirCS ; java serverSide.main.ServerConcentrationSite 22317 l040101-ws01.ua.pt 22312'