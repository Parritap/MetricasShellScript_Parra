#!/bin/bash

# Obtener la fecha del día anterior
yesterday=$(date -d "yesterday" +%c)
day=$( echo $yesterday | awk '{print $2}' )
month=$( echo $yesterday | awk '{print $3}' )

# Ruta al archivo syslog
syslog_file="/var/log/syslog"

# Ruta de la fuente de datos
data_path="../../../../../resources/data/datosHoras.txt"

printf '' > $data_path

# Recorrer las horas del día anterior y obtener la cantidad de logs por hora
for hour in $( seq 0 23 ); do

    # Filtrar los logs del archivo syslog en el intervalo de una hora
    log_count=$( cat "$syslog_file" | awk '{print $1" "$2" "$3}' | grep -i "^$month $day" | awk '{print $3}' | grep -c "^$( printf '%02d' $hour )" )

    # Imprimir el resultado en el formato "[hora, cantidad]"
    printf "[$hour,$log_count] " >> $data_path
done

