#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
mkdir -p venv
cd venv
virtualenv -p /usr/bin/python3 python3
source ./python3/bin/activate
pip3 install -r requirements.txt
export DJANGO_SETTINGS_MODULE=practice_app.prod_settings
nohup