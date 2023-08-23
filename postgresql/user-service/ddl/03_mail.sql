CREATE TABLE IF NOT EXISTS user_service.mail
(
    id bigint NOT NULL,
    mail character varying(255),
    send boolean NOT NULL,
    CONSTRAINT mail_pkey PRIMARY KEY (id)az
)