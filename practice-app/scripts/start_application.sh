#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
echo "Starting Application Practice App"
(source ./venv/python3/bin/activate &&
python manage.py migrate &&
python manage.py collectstatic &&
uwsgi uwsgi-conf.ini &&
echo "Successfully started Practice App") ||
(>&2 echo "Failed to start Practice App")