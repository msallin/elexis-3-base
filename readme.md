# Elexis Ungrad Base Repository

This is a fork of [Elexis classic base](http://github.com/elexis/elexis-3-base) from the original creator of Elexis.

## Current branches:

* ungrad2022 - Latest stable branch. Will always compile and work
* develop - Development branch. Will not always compile and mostly not work flawless

## Build:

Prerequisites: Git, Java-sdk8, Maven 3.6.x; Linux or Windows recommended. MacOS will be a bit tricky,
You'll need also the [elexis-3-core](https://github.com/rgwch/elexis-3-core) already built.
i.e._

```bash
git clone -b ungrad2022 elexis-3-core
cd elexis-3-core
./build.sh
cd ..
git clone -b ungrad2022 elexis-3-base
cd elexis-3-base
./build.sh

```

You'll find the created repository in `ch.elexis.base.p2site/target/repository`. You can use this repository from a running Elexis core via 'Help-Install new Software'.

## Develop

Please follow the instructions [here](https://github.com/rgwch/elexis-3-core/blob/develop/readme.md).

Import the elexis-3-base projects just the same way you did for the elexis-3-core projects in the same workspace. I recommend to create a separate working set "base" for the base projects.
