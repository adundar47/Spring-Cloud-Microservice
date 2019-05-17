mongo:
	docker-compose -f docker-compose.yml up -d mongo

eureka-server:
	docker-compose -f docker-compose.yml up -d eureka-server

edge-server:
	docker-compose -f docker-compose.yml up -d edge-server

kafka:
	docker-compose -f docker-compose.yml up -d zookeeper
	docker-compose -f docker-compose.yml up -d kafka

user-service:
	docker-compose -f docker-compose.yml up -d user-service

message-service:
	docker-compose -f docker-compose.yml up -d message-service

serve:
	docker-compose -f docker-compose.yml up -d nginx
	docker-compose -f docker-compose.yml up -d mongo
	docker-compose -f docker-compose.yml up -d eureka-server
	docker-compose -f docker-compose.yml up -d edge-server
	docker-compose -f docker-compose.yml up -d zookeeper
	docker-compose -f docker-compose.yml up -d kafka
	docker-compose -f docker-compose.yml up -d user-service
	docker-compose -f docker-compose.yml up -d message-service

serve-message-service-local:
	docker-compose -f docker-compose.yml up -d nginx
	docker-compose -f docker-compose.yml up -d mongo
	docker-compose -f docker-compose.yml up -d eureka-server
	docker-compose -f docker-compose.yml up -d edge-server
	docker-compose -f docker-compose.yml up -d zookeeper
	docker-compose -f docker-compose.yml up -d kafka
	docker-compose -f docker-compose.yml up -d user-service

serve-user-service-local:
	docker-compose -f docker-compose.yml up -d nginx
	docker-compose -f docker-compose.yml up -d mongo
	docker-compose -f docker-compose.yml up -d eureka-server
	docker-compose -f docker-compose.yml up -d edge-server
	docker-compose -f docker-compose.yml up -d zookeeper
	docker-compose -f docker-compose.yml up -d kafka
	docker-compose -f docker-compose.yml up -d message-service

stop-and-remove-non-stateful:
	docker-compose stop eureka-server
	docker-compose stop edge-server
	docker-compose stop zookeeper
	docker-compose stop kafka
	docker-compose stop user-service
	docker-compose stop message-service
	docker-compose rm -f

build:
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build

clean:
	docker-compose down --rmi local -v --remove-orphans

clean-deep:
	$(MAKE) clean
	sudo rm -rf /mongodb-data
	sudo rm -rf /zookeeper-data
	sudo rm -rf /kafka-data

ps:
	@docker-compose ps

new-version: new-eureka-server-version new-edge-server-version new-user-service-version new-message-service-version

new-eureka-server-version:
	docker-compose rm -f eureka-server
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build eureka-server

new-edge-server-version:
	docker-compose rm -f edge-server
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build edge-server

new-user-service-version:
	docker-compose rm -f user-service
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build user-service

new-message-service-version:
	docker-compose rm -f message-service
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build message-service

logs-eureka-server:
	docker-compose logs -f eureka-server

logs-edge-server:
	docker-compose logs -f edge-server

logs-user-service:
	docker-compose logs -f user-service	

logs-message-service:
	docker-compose logs -f message-service		

logs-kafka:
	docker-compose logs -f kafka		