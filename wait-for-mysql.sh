#!/bin/sh

echo "Waiting for MySQL to be ready..."

# Keep trying to connect to MySQL until successful
while ! mysqladmin ping -h"mysql" --silent; do
  sleep 2
done

echo "MySQL is up - starting the app"
exec java -jar app.jar