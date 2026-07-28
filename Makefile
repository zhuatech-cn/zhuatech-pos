# Copyright 2026 上海如静知华信息科技有限公司
.PHONY: dev-demo build up down test

dev-demo:
	cd frontend && npm run dev:demo

build:
	cd frontend && npm run build

up:
	docker compose up --build -d

down:
	docker compose down

test:
	cd backend && mvn test
