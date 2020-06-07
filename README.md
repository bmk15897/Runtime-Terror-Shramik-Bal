# Shramik Bal (Shramik बल)
### Team Name - Runtime-Terror 
## Contents

1. [Short description](#short-description)
1. [Demo video](#demo-video)
1. [The architecture](#the-architecture)
1. [Long description](#long-description)
1. [Project roadmap](#project-roadmap)
1. [Getting started](#getting-started)
1. [Built with](#built-with)
1. [Versioning](#versioning)
1. [Contributors](#contributors)

## Short description

### What's the problem?

Covid 19 – a global pandemic has impacted India in a huge way. The Migrant issue has emerged as an after effect of preventive measures taken to restrict COViD-19 spread in India. Upon observing this problem, we concluded that labour resources in our country are not organized and don’t have access to a common platform which can give them access to opportunities suited to their residence location, competencies and skills. This also highlights that organizations with requirements for labour resources, don’t have access to them readily in their respective states.

### How can technology help?

Technology can help bridge the gap between laborers and industries in various sectors like construction, agriculture, public sectors directly by rooting out middle men.

### The idea

As a solution, we propose Shramik Bal – A mobile application which will work as the single biggest platform to serve over 350 million workers in India. This application will aim to mitigate the migration issue by helping labourers to find job opportunities within their residential vicinity, starting with displaying jobs in current city, then current state followed by adjoining states. It will also allow our labour force to be more organized and will allow maximum utilization of their potential. It is inspired by the difficulty faced by the migrant workers while travelling back to their hometowns during the CoVID-19 pandemic.

## Demo video

[![Watch the video](https://github.com/bmk15897/Runtime-Terror-Shramik-Bal/blob/master/f53d86b3-4841-4087-b084-87718360387e_200x200.png)](https://youtu.be/vOgCOoy_Bx0)

## The architecture

![Shramik Bal Application](https://github.com/bmk15897/Runtime-Terror-Shramik-Bal/blob/master/Runtime%20Terror_Shramik%20Bal_Architecture.JPG)

1. Labourers and contractors open the mobile application and register.
2. Contractors create requirement applications using the forms as per the requirements.
3. Labourers surf for the contractor applications and apply.
4. Contractors can approve or reject the workers' requests.
5. Labourers and contractors can view notifications and update their profile details too.

## Long description

#### Profiles 
There are two User Profiles – Laborers and Contractors. The Contractors can register as individuals whereas the laborers have option to register as individuals or register as group.  The laborers tend to move in groups thus the option to operate as an individual or operate as a group will enable them to do so if required. The New profile registration for both user profiles will have to go through mandatory phone OTP verification step (we are yet to implement this in real time). All users will provide direct input into the system –Profile Information like basic details, locality, Phone Number (Mandatory), skill set,etc.

Anyone looking for resources can post the requirement and nature of the task .The interested users (laborers) can look up on the app and apply for the job openings available in their vicinity which will help minimize logistical challenges. A contractor can accept or decline the request created as per his/her requirement.

#### Languages

Currently application is available in Hindi (national language) and English

##### Sustainability

The application will be free cost for the contractors for the first month (from the date of registration) so we can build a customer base. Post which we plan to charge a subscription fee for them on the basis of number of profiles required. No fees will be charged to labourers.

##### Go Live Approach

We will conduct awareness campaigns and training sessions across India. In order to build a database we will explore options of existing databases that contain information about laborers. Once we have data of at least 20K laborers per state we will target to roll out the application to laborers and contractors.

[More detail is available here](https://github.com/bmk15897/Runtime-Terror-Shramik-Bal/blob/master/Women%20Hackathon_Runtime%20Terror_Participation.pdf)

## Project roadmap

![Roadmap](https://github.com/bmk15897/Runtime-Terror-Shramik-Bal/blob/master/Runtime%20Terror_Shramik%20Bal_Road%20Map.JPG)

## Getting started

### Prerequisites

Installations required - 

+ Java 1.8+
+ Node.js (Node is bundled with npm, the package manager for JavaScript.)

To verify installations - 
```
node --version
npm --version
```
+ Ionic cli 

To verify intallations -
```
npm install -g @ionic/cli
```
+ STS 4.6.1.RELEASE

### Installing

To start the ionic application - 

``` 
ionic serve
```

Start the springboot appplication from the STS IDE.

## Built with

* [IBM Cloud DB2](https://cloud.ibm.com/catalog/services/db2) - The DB2 RDBMS database used
* [Ionic](https://ionicframework.com/) - The mobile frontend framework used
* [STS 4.6.1](https://spring.io/tools) - The IDE used for springboot application
* [VS CODE](https://code.visualstudio.com/) - The IDE used for ionic application
* [Maven](https://maven.apache.org/) - Dependency management
* [Git](https://git-scm.com/) - The distributed version control system used
* [PBKDF2WithHmacSHA1](https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/) - The hashing algorithm used for storing passwords

## Versioning

We used [Git](https://git-scm.com/) for versioning.

## Contributors

* **Nensukh Ghute** - *Mentor*
* **Minu Raju** - *Idea initiator and Business Analyst*
* **Shreya Joshi** - *Frontend Development*
* **Bharati Kulkarni** - *Backend Development*
* **Prajakta Patankar** - *Documentation*
* **Neha Rathod** - *Documentation*


See also the list of [contributors](https://github.com/bmk15897/Runtime-Terror-Shramik-Bal/graphs/contributors) who participated in the development of this project.
