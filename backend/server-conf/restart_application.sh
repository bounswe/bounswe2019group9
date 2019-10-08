#!/usr/bin/env bash
cd /home/ec2-user
echo "Restarting Application Bounswe Backend"
(sudo service spring-boot-ec2-demo restart &&
echo "Successfully restarted Bounswe Backend") ||
(>&2 echo "Failed to restart Bounswe Backend")
