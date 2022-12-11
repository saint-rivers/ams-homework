build:
	./gradlew build -x test

deploy:
	docker-compose up -d --build
	
down:
	docker-compose down

nginx:
	cp nginx/ams.saintrivers.tech.conf /etc/nginx/conf.d/ams.saintrivers.tech.conf && \
		certbot --nginx -d ams.saintrivers.tech
