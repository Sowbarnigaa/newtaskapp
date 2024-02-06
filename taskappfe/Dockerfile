
FROM node:21-alpine3.18 as angular

WORKDIR /app

COPY . .
RUN npm install
RUN npm run build --prod

CMD ["npm", "start"]

# FROM node:latest as build
# RUN mkdir /app
# WORKDIR /app
# RUN npm install -g @angular/cli
# COPY package.json package-lock.json ./
# RUN npm ci
# COPY . .
# EXPOSE 4200
# CMD ["ng", "serve", "--host", "0.0.0.0"]
