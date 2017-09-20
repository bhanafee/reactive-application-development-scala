
project was the original HelloRemoting-master

renamed to be more consistent with the other projects

To run the project:

1.  to run:

	open a terminal for the guidebook actor system.  To run:

		sbt "-Dakka.remote.netty.tcp.port=2553" "runMain GuidebookMain"


	open a terminal for the tourist actor system.  To run:

		sbt "runMain TouristMain"


    you will see (mock) tracing around the messages between guidebook and tourist

2.  type Ctrl-C to stop the actor system


