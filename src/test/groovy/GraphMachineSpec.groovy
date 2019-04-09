import edu.sperek.graphlab.Edge
import edu.sperek.graphlab.GraphModel
import edu.sperek.graphlab.Vertex
import kotlin.Pair
import spock.lang.Specification

class GraphMachineSpec extends Specification {
    def edges
    GraphModel graphModel

    def setup() {
        edges = [new Edge(new Vertex(1), (new Vertex(2))), new Edge(new Vertex(2), (new Vertex(3))), new Edge(new Vertex(3), (new Vertex(4))), new Edge(new Vertex(1), (new Vertex(4))), new Edge(new Vertex(2), (new Vertex(4)))]
        graphModel = new GraphModel(4, 5, edges as ArrayList<Edge>)
    }

    def 'Should return the number of vertices'() {
        given: "a graph"
        when: "user retrieves number of vertices"
        int vertices = graphModel.order
        then:
        vertices == 4
    }

    def 'Should return list of vertices'() {
        given: "a graph"
        when: "user retrieves list of vertices"
        def verticesList = graphModel.verticesList
        then:
        verticesList == [new Vertex(1), new Vertex(2), new Vertex(3), new Vertex(4)]
    }

    def 'Should return the number of edges'() {
        given: "a graph"
        when: "user retrieves for number of edges"
        def numberOfEdges = graphModel.size
        then:
        numberOfEdges == 5
    }

    def 'Should return list of edges'() {
        given: "a graph"
        when: "user retrieves for list of edges"
        def edges = graphModel.edgesList
        then:
        edges == ["1-2", "2-3", "3-4", "1-4", "2-4"]
    }

    def 'Should return an adjacency matrix'() {
        given: "a graph"
        when: "user retrieves for adjacency matrix"
        def adjacencyMatrix = graphModel.adjacencyMatrix
        def expectedMatrix = [
                [0, 1, 0, 1],
                [1, 0, 1, 1],
                [0, 1, 0, 1],
                [1, 1, 1, 0]
        ] as Integer[][]
        then:
        expectedMatrix == adjacencyMatrix
    }

    def 'Should return an incidence matrix'() {
        given: "a graph"
        when: "user retrieves for incidence matrix"
        def incidenceMatrix = graphModel.incidenceMatrix
        def expectedMatrix = [
                [1, 0, 0, 1, 0],
                [1, 1, 0, 0, 1],
                [0, 1, 1, 0, 0],
                [0, 0, 1, 1, 1]
        ] as Integer[][]
        then:
        expectedMatrix == incidenceMatrix
    }

    def 'Should return the list of vertices degrees'() {
        given: "a graph"
        when: "user retrieves for vertices degrees"
        def verticesDegrees = graphModel.verticesDegrees
        def expectedDegrees = [
                new Pair(new Vertex(1), 2),
                new Pair(new Vertex(2), 3),
                new Pair(new Vertex(3), 2),
                new Pair(new Vertex(4), 3)
        ] as List<Pair>
        then:
        expectedDegrees == verticesDegrees
    }

    def 'Should return the sequence of graph degrees'() {
        given: "a graph"
        when: "user retrieves for sequence of graph degrees"
        def graphDegreesSequence = graphModel.degreesSequence
        def expectedDegreesSequence = [
                2, 2, 3, 3
        ] as List<Integer>
        then:
        expectedDegreesSequence == graphDegreesSequence

    }

    def 'Should check if graph is simple'() {
        given: "a graph"
        when: "user checks if graph is simple"
        def isSimple = graphModel.isSimple()
        def expected = true
        then:
        expected == isSimple
    }

    def 'Should return needed edges to make complete graph '() {
        given: "a graph"
        when: "user checks for edges needed to make complete graph"
        def fulfillingEdges = graphModel.fulfillingEdges
        def expected = [new Edge(1, 3)] as List<Edge>
        then:
        fulfillingEdges == expected
    }

    def 'Should return list of vertices with their neighbors'() {
        given: "a graph"
        when: "user checks map of vertices with neighbors"
        def listOfNeighbors = graphModel.getNeighbors
        Map<Vertex, Set<Vertex>> expected = new HashMap<>()
        expected.put(new Vertex(1),[new Vertex(2), new Vertex(4)].toSet())
        expected.put(new Vertex(2),[new Vertex(1), new Vertex(3), new Vertex(4)].toSet())
        expected.put(new Vertex(3),[new Vertex(2), new Vertex(4)].toSet())
        expected.put(new Vertex(4),[new Vertex(1), new Vertex(2), new Vertex(3)].toSet())
        then:
        listOfNeighbors == expected
    }
}
