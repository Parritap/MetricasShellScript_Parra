#!/bin/bash
last --since "3 month ago"| grep -v logout | grep seat0 | awk '{print $1,$NF}' | sed 's/[()]//g' | python3 UsTimeCalc.py
