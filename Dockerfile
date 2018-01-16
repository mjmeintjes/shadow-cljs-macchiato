FROM mhart/alpine-node:latest

MAINTAINER Your Name <you@example.com>

# Create app directory
RUN mkdir -p /funnels-cljs
WORKDIR /funnels-cljs

# Install app dependencies
COPY package.json /funnels-cljs
RUN npm install pm2 -g
RUN npm install

# Bundle app source
COPY target/release/funnels-cljs.js /funnels-cljs/funnels-cljs.js
COPY public /funnels-cljs/public

ENV HOST 0.0.0.0

EXPOSE 3000
CMD [ "pm2-docker", "/funnels-cljs/funnels-cljs.js" ]
