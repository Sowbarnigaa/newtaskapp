# FROM node:latest as builder
# RUN mkdir -p /app

# WORKDIR /app

# COPY . .

# RUN npm install
# RUN npm run build --prod

# CMD ["npm", "start"]
FROM node:21-alpine3.18 as angular

WORKDIR /app

COPY . .
# RUN npm ci
# RUN npm run build --prod

CMD ["npm", "start"]
