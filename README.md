# UnisaMyAdmin

## Project configurations
### Maven enforcer
[Maven Enforcer](https://maven.apache.org/enforcer/maven-enforcer-plugin/index.html) is used to guarantee consistent dependency versions across team members.
It also validates that the correct JDK and Maven versions are used

### Maven multithreaded builds
In the `.mvn` directory is a configuration file `maven.config` that starts maven with multiple threads. This
allows faster builds on systems with more than one virtual CPU (most of recent PCs).