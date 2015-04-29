# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table movie (
  id                        integer not null,
  title                     varchar(255),
  director                  varchar(255),
  constraint pk_movie primary key (id))
;

create sequence movie_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists movie;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists movie_seq;

