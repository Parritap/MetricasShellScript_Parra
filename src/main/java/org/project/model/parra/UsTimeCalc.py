#!/usr/bin/env python3
# El siguiente script calcula el tiempo de uso de cada usuario en minutos
# y lo guarda en un archivo de texto llamado userUsage.txt
# El archivo de texto se encuentra en la carpeta resources/data
import sys

users = {}

for line in sys.stdin:
    line = line.strip()
    if line:
        parts = line.split()
        if len(parts) == 2:
            user = parts[0]
            time = parts[1]
            if ':' in time:
                hours, minutes = time.split(':')
                total_minutes = int(hours) * 60 + int(minutes)
                users[user] = users.get(user, 0) + total_minutes

with open("../../../../../resources/data/userUsage.txt", "w") as file:
    for user, minutes in users.items():
        file.write(f"[{user},{minutes}] ")
