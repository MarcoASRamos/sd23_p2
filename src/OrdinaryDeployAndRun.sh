# local execution
#echo "Executing the ordinary node."
#cd /home/marco/Desktop/MuseumHeist/dirOrdinary
#java -cp "../genclass.jar:." clientSide.main.ClientOrdinary 127.0.0.1 22313 127.0.0.1 22314 127.0.0.1 22315 127.0.0.1 22316 127.0.0.1 22317 127.0.0.1 22312
#echo "ordinary Server shutdown."

# global execution
echo "Transfering data to the ordinary node."
sshpass -f password ssh sd403@l040101-ws09.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd403@l040101-ws09.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirOrdinary.zip sd403@l040101-ws09.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Ordinary node."
sshpass -f password ssh sd403@l040101-ws09.ua.pt 'cd test/MuseumHeist ; unzip -uq dirOrdinary.zip'
sshpass -f password scp genclass.zip sd403@l040101-ws09.ua.pt:test/MuseumHeist/dirOrdinary
sshpass -f password ssh sd403@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinary ; unzip -uq genclass.zip'
echo "Executing program at the Ordinary node."
sshpass -f password ssh sd403@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinary ; java clientSide.main.ClientOrdinary l040101-ws06.ua.pt 22426 l040101-ws05.ua.pt 22425 l040101-ws03.ua.pt 22423 l040101-ws02.ua.pt 22422 l040101-ws01.ua.pt 22421 l040101-ws07.ua.pt 22427'