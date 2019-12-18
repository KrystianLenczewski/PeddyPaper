package pt.ubi.di.pmd.peddypaper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CalculateRoute {

    class DirectedEdge {
        // wierzcholek zrodlowy
        private final int from;
        // wierzcholek docelowy
        private final int to;
        // waga
        private final long weight;

        public DirectedEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int from() {
            return from;
        }

        public int to() {
            return to;
        }

        public long getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return String.format("%d->%d (%d) ", from, to, weight);
        }
    }
    class DirectedGraph {
        // liczba wierzcholkow
        private final int v;
        // liczba krawedzi
        private int e;
        // listy sasiedztwa
        private final List<DirectedEdge>[] neighborhoodLists;

        @SuppressWarnings("unchecked")
        public DirectedGraph(int v) {
            this.v = v;
            this.e = 0;
            this.neighborhoodLists = (List<DirectedEdge>[]) new List[v];
            for (int i = 0; i < v; i++) {
                neighborhoodLists[i] = new ArrayList<>();
            }
        }

        public int getNumberOfEdges() {
            return e;
        }

        public int getNumberOfVertices() {
            return v;
        }

        /**
         * Dodaje krawedz skierowana do odpowiedniej listy sasiedztwa, listy
         * wierzcholka, z ktorej zaczyna sie krawedz.
         *
         * @param edge
         *            krawedz, ktora ma zostac dodana do grafu
         */
        public void addEdge(DirectedEdge edge) {
            neighborhoodLists[edge.from()].add(edge);
            e++;
        }

        /**
         * Zwraca liste sasiedztwa danego wierzcholka.
         *
         * @param v
         *            indeks wierzcholka skierowanego
         * @return zwraca iterowalna kolekcje krawedzi skierowanych
         */
        public Iterable<DirectedEdge> getNeighborhoodList(int v) {
            return neighborhoodLists[v];
        }
    }

    public class DijkstraShortestPath {

        /**
         * Reprezentuje dystans do danej krawedzi. Uzywane do przechowywania w
         * kolejce priorytetowej do wyboru najkrotszej krawedzi.
         *
         * @author mephisto
         */
        class DistanceToEdge implements Comparable<DistanceToEdge> {
            // krawedz
            private final int edge;
            // odleglosc do tej krawedzi
            private long distance;

            public DistanceToEdge(int edge, long distance) {
                this.edge = edge;
                this.distance = distance;
            }

            public long getDistance() {
                return distance;
            }

            public void setDistance(long distance) {
                this.distance = distance;
            }

            public int getEdge() {
                return edge;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + getOuterType().hashCode();
                result = prime * result + (int) (distance ^ (distance >>> 32));
                result = prime * result + edge;
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                DistanceToEdge other = (DistanceToEdge) obj;
                if (!getOuterType().equals(other.getOuterType()))
                    return false;
                if (distance != other.distance)
                    return false;
                if (edge != other.edge)
                    return false;
                return true;
            }

            @Override
            public int compareTo(DistanceToEdge param) {
                int cmp = new Long(distance).compareTo(param.getDistance());

                if (cmp == 0) {
                    return new Integer(edge).compareTo(param.getEdge());
                }
                return 0;
            }

            private DijkstraShortestPath getOuterType() {
                return DijkstraShortestPath.this;
            }
        }

        // ////////////////////////////////////////////////////////////////////
// przechowuje krawedz z ktorej jest najblizej do biezacej okreslonej
// indeksem tablicy
        private DirectedEdge[] edgeTo;
        // przechowuje najkrotsze znalezione odleglosci do danego wierzcholka
        private Long[] distanceTo;
        // kolejka przechowujaca biezace krawedzie uszeregowane od najkrotszej do
// najdluzszej
        private Queue<DistanceToEdge> priorityQueue;

        public DijkstraShortestPath(DirectedGraph graph, int source) {
// inicjacja wewnetrznych struktur
            edgeTo = new DirectedEdge[graph.getNumberOfVertices()];
            distanceTo = new Long[graph.getNumberOfVertices()];
            priorityQueue = new PriorityQueue<DistanceToEdge>(
                    graph.getNumberOfVertices());

// dla kazdego wierzcholka v ustawiamy najwieksza mozliwa wartosc jaka
// moze przechowywac
            for (int v = 0; v < graph.getNumberOfVertices(); v++) {
                distanceTo[v] = Long.MAX_VALUE;
            }
// odleglosc do wierzcholka zrodlowego to 0
            distanceTo[source] = 0L;

// wstawiamy wierzholek zrodlowy do kolejki, od niego rozpoczynamy
// poszukiwania
            priorityQueue.offer(new DistanceToEdge(source, 0L));

// przeprowadzamy relaksacje grafu dopoki kolejka nie jest pusta
            while (!priorityQueue.isEmpty()) {
                relax(graph, priorityQueue.poll().getEdge());
            }

        }

        private void relax(DirectedGraph graph, int v) {
// przegladamy listy sasiedztwa wszystkich wierzcholkow
            for (DirectedEdge edge : graph.getNeighborhoodList(v)) {
                int w = edge.to();

// sprawdzamy czy zmiana wierzcholka skroci dystans z wierzcholka
// zrodlowego
                if (distanceTo[w] > distanceTo[v] + edge.getWeight()) {
                    distanceTo[w] = distanceTo[v] + edge.getWeight();
                    edgeTo[w] = edge;
                    DistanceToEdge dte = new DistanceToEdge(w, distanceTo[w]);

// jesli jest juz krawedz o tej wadze to ja usuwamy, bo
// znalezlismy lepsza droge
                    priorityQueue.remove(dte);
// wstawiamy nowa krawedz z aktualna znaleziona odlegloscia
                    priorityQueue.offer(dte);
                }
            }

        }

        // jesli zwrocona wartosc wynosi Long.MAX_VALUE to oznacza, ze dany
// wierzcholek jest nieosiagalny ze zrodla
        public long getDistanceTo(int v) {
            return distanceTo[v];
        }

        // jesli istnieje droga do danego wierzcholka jest mniejsza od
// Long.MAX_VALUE, ktore zostalo inicjalnie ustawione dla wszystkich
// wierzcholkow
        public boolean hasPathTo(int v) {
            return distanceTo[v] < Long.MAX_VALUE;
        }

        // jesli nie istnieje sciezka do danego wierzcholka zostanie zwrocona pusta
// kolekcja
        public Iterable<DirectedEdge> getPathTo(int v) {
            Deque<DirectedEdge> path = new ArrayDeque<DirectedEdge>();
// jesli nie istnieje sciezka zwroc pusta sciezke
            if (!hasPathTo(v)) {
                return path;
            }
// dopoki istnieje krawedz dodawaj ja do stosu
// krawedz zrodlowa jest oznaczona jako null
            for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
                path.push(e);
            }
            return path;
        }


        public  void main(String[] args) {
// Rozwiazanie przykladu z lekcji HEGARTYMATHS
// http://www.youtube.com/watch?v=xT5o1QCeWS8
            DirectedGraph graph = new DirectedGraph(28);
            graph.addEdge(new DirectedEdge(0, 1, 43));
            graph.addEdge(new DirectedEdge(0, 4, 60));
            graph.addEdge(new DirectedEdge(0, 8, 10));
            graph.addEdge(new DirectedEdge(0, 20, 220));
            graph.addEdge(new DirectedEdge(0, 26, 12));

//graph.addEdge(new DirectedEdge(2, 1, 43));

            graph.addEdge(new DirectedEdge(2, 13, 78));
            graph.addEdge(new DirectedEdge(2, 16, 35));

            graph.addEdge(new DirectedEdge(3, 5, 10));
            graph.addEdge(new DirectedEdge(3, 25, 80));

//graph.addEdge(new DirectedEdge(4, 0, 60));

//graph.addEdge(new DirectedEdge(5, 4, 10));
            graph.addEdge(new DirectedEdge(5, 25, 70));

            graph.addEdge(new DirectedEdge(6, 7, 40));
            graph.addEdge(new DirectedEdge(6, 27, 50));

//graph.addEdge(new DirectedEdge(8, 7, 40));
            graph.addEdge(new DirectedEdge(7, 9, 120));
            graph.addEdge(new DirectedEdge(7, 20, 70));

//graph.addEdge(new DirectedEdge(9, 1, 10));
            graph.addEdge(new DirectedEdge(8, 24, 15));


            graph.addEdge(new DirectedEdge(9, 20, 47));

            graph.addEdge(new DirectedEdge(10, 11, 20));
            graph.addEdge(new DirectedEdge(10, 17, 170));

            graph.addEdge(new DirectedEdge(11, 12, 20));

            graph.addEdge(new DirectedEdge(12, 17, 130));
            graph.addEdge(new DirectedEdge(12, 21, 54));
            graph.addEdge(new DirectedEdge(12, 27, 84));

            graph.addEdge(new DirectedEdge(13, 14, 10));
            graph.addEdge(new DirectedEdge(13, 16, 74));

            graph.addEdge(new DirectedEdge(14, 15, 10));

            graph.addEdge(new DirectedEdge(16, 26, 84));

            graph.addEdge(new DirectedEdge(17, 19, 130));
            graph.addEdge(new DirectedEdge(17, 21, 130));
            graph.addEdge(new DirectedEdge(17, 26, 40));
            graph.addEdge(new DirectedEdge(17, 27, 90));

            graph.addEdge(new DirectedEdge(18, 19, 90));
            graph.addEdge(new DirectedEdge(18, 23, 100));


            graph.addEdge(new DirectedEdge(20, 8, 230));

            graph.addEdge(new DirectedEdge(21, 22, 10));
            graph.addEdge(new DirectedEdge(21, 27, 10));

            graph.addEdge(new DirectedEdge(22, 27, 10));

            graph.addEdge(new DirectedEdge(23, 21, 400));
            graph.addEdge(new DirectedEdge(23, 22, 410));
            graph.addEdge(new DirectedEdge(23, 25, 30));

            graph.addEdge(new DirectedEdge(26, 16, 40));
            graph.addEdge(new DirectedEdge(26, 0, 12));
            graph.addEdge(new DirectedEdge(26, 17, 40));

            graph.addEdge(new DirectedEdge(27, 6, 50));
            graph.addEdge(new DirectedEdge(27, 21, 10));
            graph.addEdge(new DirectedEdge(27, 22, 10));
            graph.addEdge(new DirectedEdge(27, 12, 84));
            int source = 0;
            DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph,
                    source);

// Wyswietla najkrotsze sciezki
            int end_point = 21;
            if (shortestPath.hasPathTo(end_point)) {
                System.out.printf(" (%d)  ",
                        shortestPath.getDistanceTo(end_point));
                if (shortestPath.hasPathTo(end_point)) {
                    for (DirectedEdge edge : shortestPath.getPathTo(end_point)) {
                        System.out.print(edge);
                    }
                }
            }
        }


    }



}
