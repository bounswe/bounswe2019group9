#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
echo "Restarting Practice App"
(./scripts/install_python_dependencies.sh &&
./scripts/stop_application.sh &&
./scripts/start_application.sh &&
echo "Successfully Restarted Practice App") ||
(>&2 echo "Failed to Restart Practice App")