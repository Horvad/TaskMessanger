FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademy

ADD /user-service/user-service.yml /usr/share/nginx/html/doc/user-service.yaml
ADD /audit-service/audit-service.yml /usr/share/nginx/html/doc/audit-service.yaml
ADD /task-service/task-service.yml /usr/share/nginx/html/doc/task-service.yaml
ADD /report-service/report-service.yml /usr/share/nginx/html/doc/report-service.yaml

ENV URLS="[{ url: 'doc/user-service.yaml', name: 'User'},{ url: 'doc/audit-service.yaml', name: 'Audit'},{ url: 'doc/task-service.yaml', name: 'Task'},{ url: 'doc/report-service.yaml', name: 'Report'}]"
ENV URLS_PRIMARY_NAME="User"