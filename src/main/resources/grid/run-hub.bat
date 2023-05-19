set GRID_SERVER=server/selenium-server-standalone-3.9.1.jar
set HUB_CONFIG=configurations/hub-config.json

java -jar %GRID_SERVER% -role hub -hubConfig %HUB_CONFIG%