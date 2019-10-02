#!/usr/bin/env bash
cd /home/ec2-user/bounswe-backend
echo "Starting Application Bounswe Backend"
(service spring-boot-bounswe-backend start &&
echo "Successfully started Bounswe Backend") ||
(>&2 echo "Failed to start Bounswe Backend")
