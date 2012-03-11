# Bacteria

Solution to the Bacteria programming challenge.


## Install steps

* Install [sbt](https://github.com/harrah/xsbt)
* Get the source:

		$ git clone git@github.com:grayt0r/bacteria.git
		$ cd bacteria

* Launch sbt:

		$ sbt

* Compile and run the code:

		> compile
		> run


## Options

* Print the grids to the console (defaults to false):

		> run --print

* Specify the height of the petri dish (defaults to 5):

		> run --height 10

* Specify the width of the petri dish (defaults to 5):

		> run --width 10


## Tests

* Launch sbt:

		$ sbt

* To run the specs2 unit tests:

		> compile
		> test