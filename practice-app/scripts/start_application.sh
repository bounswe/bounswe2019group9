#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
echo "Starting Application Practice App"
(source ./venv/bin/activate &&
python manage.py migrate --settings=practice_app.prod_settings &&
python manage.py collectstatic --settings=practice_app.prod_settings &&
uwsgi uwsgi-conf.ini &&
echo "Successfully started Practice App") ||
(>&2 echo "Failed to start Practice App")