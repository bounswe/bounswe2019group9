#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
echo "Stopping Practice App"
(source ./venv/python3/bin/activate &&
uwsgi --stop practice_app.uwsgi.pid &&
echo "Successfully stopped Practice App") ||
(>&2 "Failed to stop Practice App")