#!/bin/bash
#El siguiente script se encarga de obtener los tiempos de inicio y fin de sesion de los usuarios
last --since "3 month ago"| grep -v logout | grep ":0" | awk '{print $1,$NF}' | sed 's/[()]//g' | python3 UsTimeCalc.py

