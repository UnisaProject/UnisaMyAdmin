# Angular Environment
---

#### Environment Requirements
As of writing this README the versions are as follows:
* Angular 5.2.7
* Node.js v8.10.0 LTS
* npm 5.4.2
* yarn 1.5.1
* Angular CLI 1.7.2

*Optionally:* Typescript Version 2.5.3 [^1]

#### (optional) Install Node Version Manager (nvm)
This is an optional step, but does help when required to change node versions between project, and for 
experimenting the affect of a newer nodejs runtime for this project.

1. Download and install the manager from [nvm-windows Github](https://github.com/coreybutler/nvm-windows/releases)
2. Make sure it is available from the system path `nvm`.
   It should print out useful commands
3. Install the required nodejs version `nvm install 8.10.0`
4. Configure to use the required version `nvm use 8.10.0`
5. Confirm the active nodejs version `ndoe --version`

If for some reason node does not appear on the path, make sure the directory C:\Program Files\nodejs exists
(create if missing), and switch version again `nvm use 8.10.0`

Keep in mind that when you change nodejs versions you have to reinstall any node modules you installed with
a previous version of nodejs as the modules are installed with the nodejs version.

Yarn on the other hand installs its packages outside of nodejs, so there is no need to reinstall modules
that was installed with yarn, you do however need to install yarn again if it was installed using npm.

##### Upgrading your environment (if necessary)
1. Upgrading [Node.js](https://nodejs.org/en/) by installing the latest package for your OS. [^2]
2. Installing Yarn: `npm install -g yarn`
3. Set Yarn as default package manager for use in Angular ClI: `ng set --global packageManager=yarn`
4. How to update [Angular CLI](https://github.com/angular/angular-cli#updating-angular-cli) if using **npm**. 
5. How to update [Angular CLI](https://www.npmjs.com/package/angular-cli-with-use-yarn#updating-angular-cli) if using **yarn**.
6. *Optionally:* installing Typescript: `npm install -g typescript@2.5.3 --save-exact`

##### Project versions *(as of this writing)*
In any of angular project folder, execute the command: `ng -v` and (hopefully) see this results:

```
   / \   _ __   __ _ _   _| | __ _ _ __     / ___| |   |_ _|
  / △ \ | '_ \ / _` | | | | |/ _` | '__|   | |   | |    | |
 / ___ \| | | | (_| | |_| | | (_| | |      | |___| |___ | |
/_/   \_\_| |_|\__, |\__,_|_|\__,_|_|       \____|_____|___|
               |___/

Angular CLI: 1.7.2
Node: 8.10.0
OS: win32 x64
Angular: 5.2.7
... animations, common, compiler, compiler-cli, core, forms
... http, language-service, platform-browser
... platform-browser-dynamic, router

@angular/cli: 1.7.2
@angular-devkit/build-optimizer: 0.3.2
@angular-devkit/core: 0.3.2
@angular-devkit/schematics: 0.3.2
@ngtools/json-schema: 1.2.0
@ngtools/webpack: 1.10.1
@schematics/angular: 0.3.2
@schematics/package-update: 0.3.2
typescript: 2.5.3
webpack: 3.11.0
```
---
#### Build the project
##### Using yarn package manager
1. Install all the dependencies for the project: `yarn install`
2. Build the project: `yarn build`
3. *Alternatively build the project with maven:* `mvn clean install -DskipTests` [^3]

##### Using npm package manager
1. Install all the dependencies for the project: `npm install`
2. Build the project: `npm build`
3. *Alternatively build the project with maven:* `mvn clean install -DskipTests` [^3]

[^1]: For creating and compiling typescript files outside an angular project.

[^2]: Node.js will also install the latest npm packager.

[^3]: The maven build process uses the (Frontend Maven Plugin)[https://github.com/eirslett/frontend-maven-plugin] to build the project. It will uses *yarn build* under the covers to create the distribution artifiacts and then packaged it all as a maven jar dependency.
