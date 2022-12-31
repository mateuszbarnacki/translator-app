# Translator App

## Table of Contents

 * [Project Overview](#project-overview)
   * [Description](#description)
   * [Team](#team)
   * [Work Planning](#work-planning)
   * [Functionalities](#functionalities)
 * [How to run locally](#how-to-run-locally)
   * [Requirements](#requirements)
   * [General](#general)
   * [Backend](#backend-a-nameinstruction-backend-a)
   * [Frontend](#frontend-a-nameinstruction-frontend-a)

## Project Overview

### Description

App works as container for messages (originally in English) and translations for them. They are used by different services to get translations.

### Team

* Mateusza Barnacki
* Michał Kacprzak
* Patryk Śledź
* Kateryna Andrusiak

### Work Planning

Trello was used as a tool to organize and plan work. Our Trello board is available under [link](https://trello.com/invite/b/Ha9Z3neA/ATTIe163c2a864d5559eca5c6aef26ce1119070B5287/translator-app).

### Functionalities

#### Backend

* App is easily expandable for new languages of translations (adding new language is available through app), same for deleting and modyfing existing languages
* User is able to get created messages and translations from database
* User is able to create new messages and translations
* User is able to modify existing messages and translations
* User is able to delete existing messages and translations
* User is able to create new tags
* User is able to modify existing tags
* User is able to delete existing tags
* User is able to list all messages, tags and languages
* User is able to search for messages using tags, languages, original message or content

#### Frontend

Currently, the application gives the user the ability to see all resources (tags, languages, message) in table format

## How to run locally

Project is not hosted anywhere so in order to test it manually user must first run backend and then frontend.
It is possible to use backend and frontend separately. To run the frontend without the backend the environment variable
`REACT_APP_DEV` must be set to `true`. Then frontend will use dummy mocked data instead real one.

### Requirements

- Docker

### General

* Check if Docker is installed and it is working - `docker version`
* Open console
* Download git repository - `git clone https://github.com/mateuszbarnacki/cisco-proj.git`
* Go to project root folder - `cd cisco-project/`

### Backend <a name="instruction-backend"></a>

* Go to backend folder - `cd api/translator`
* Using Docker Compose build Docker image and fire up container - `docker-compose -f docker-compose.yml up -d --build`
* Navigate to http://localhost:8080/ in your browser to view the app

### Frontend <a name="instruction-frontend"></a>

* Go to frontend folder - `cd frontend/`
* Using Docker Compose build Docker image and fire up container - `docker-compose -f docker-compose.yml up -d --build`, if you want to run frontend without backend use `docker-compose.dev.yml` file
* Navigate to http://localhost:1337/ in your browser to view the app