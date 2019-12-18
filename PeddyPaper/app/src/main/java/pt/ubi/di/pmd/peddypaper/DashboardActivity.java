package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DashboardActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String EmailHolder;
    TextView Email;
    SQLiteHelper datahelper;
    Button LogOUT ;
    Button seeRoute;
    private Spinner spinner_start_point;
    private Spinner spinner_end_point;
    ArrayList<String> list_end;
    ArrayAdapter<String> adapter_end;
    private int array[]=new int[10];
    int i=0;
    public void function(final int started_point,final int ended_point)
    {
        array[1]=started_point;
        array[2]=ended_point;

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


            public void addEdge(DirectedEdge edge) {
                neighborhoodLists[edge.from()].add(edge);
                e++;
            }

            public Iterable<DirectedEdge> getNeighborhoodList(int v) {
                return neighborhoodLists[v];
            }
        }


         class DijkstraShortestPath {


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

                if (!hasPathTo(v)) {
                    return path;
                }

                for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
                    path.push(e);
                }
                return path;
            }

   public  void main(String[] args) {

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


                int source = 0;
                DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph,
                        source);
                array[1]=1;
                array[2]=2;
                // Wyswietla najkrotsze sciezki
                int end_point=2;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
         datahelper=new SQLiteHelper(this);
        Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);
        seeRoute = (Button)findViewById(R.id.seeRoute);

        Intent intent = getIntent();
        spinner_start_point=(Spinner)findViewById(R.id.static_spinner);
        spinner_end_point=(Spinner)findViewById(R.id.spinner_end_point);

        ArrayList<String> list=datahelper.getAllPoints();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list);
        spinner_start_point.setAdapter(adapter);

        list_end=datahelper.getAllPoints();
       adapter_end=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list_end);

        spinner_end_point.setAdapter(adapter_end);


        spinner_start_point.setOnItemSelectedListener(this);
        spinner_end_point.setOnItemSelectedListener(this);


        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(MainActivity.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(DashboardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });
        seeRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Intent intent=new Intent(DashboardActivity.this,RouteInformationActivity.class);
             intent.putExtra("userEmail",EmailHolder);
             startActivity(intent);
                function(id_start_point,id_end_point);
                datahelper.newTableForUser(EmailHolder);


                Cursor data = datahelper.getData();

                int itemID = -1;
                String nameOfPoint="";

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                    nameOfPoint=data.getString(1);
                    i=1;
                    for(int i=1;i<=6;i++){
                        if(array[i]==itemID) {
                            datahelper.insertData(nameOfPoint, EmailHolder);
                        }

                    }

                }
                for(int i=1;i<=6;i++){
                    array[i]=0;

                }
                }

        });

    }
    int id_start_point;
    int id_end_point;
    int k=0;
    String tempRemovedItem ;
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if(parent.getId() == spinner_start_point.getId())
        {
            k=1;
            switch (position) {
                case 0:
                    id_start_point=1;
                    break;
                case 1:
                    id_start_point=2;
           // array[1]=2;


                    break;
                case 2:
                    id_start_point=3;
                    Toast.makeText(DashboardActivity.this,"You choose position 3", Toast.LENGTH_SHORT).show();
                   // array[2]=3;



                    break;
                case 3:
                    id_start_point=4;
                    Toast.makeText(DashboardActivity.this,"You choose position 3", Toast.LENGTH_SHORT).show();
                   // array[3]=4;


                    break;
                case 4:
                    id_start_point=5;
                    break;
                case 5:
                    id_start_point=6;
                    break;
                case 6:
                    id_start_point=7;
                    break;
                case 7:
                    id_start_point=8;
                    break;
                case 8:
                    id_start_point=9;
                    break;
                case 9:
                    id_start_point=10;
                    break;
                case 10:
                    id_start_point=11;
                    break;
                case 11:
                    id_start_point=12;
                    break;
                case 12:
                    id_start_point=13;
                    break;
                case 13:
                    id_start_point=14;
                    break;
                case 14:
                    id_start_point=15;
                    break;
                case 15:
                    id_start_point=16;
                    break;
                case 16:
                    id_start_point=17;
                    break;

                case 17:
                    id_start_point=18;
                    break;

                case 18:
                    id_start_point=19;
                    break;
                case 19:
                    id_start_point=20;
                    break;
                case 20:
                    id_start_point=21;
                    break;
                case 21:
                    id_start_point=22;
                    break;
                case 22:
                    id_start_point=23;
                    break;
                case 23:
                    id_start_point=24;
                    break;
                case 24:
                    id_start_point=25;
                    break;
                case 25:
                    id_start_point=26;
                    break;
                case 26:
                    id_start_point=27;
                    break;
                case 27:
                    id_start_point=28;
                    array[5]=id_start_point;
                    break;
            }
        }

   /* if(k==1) {

    k=0;
    list_end.remove(spinner_start_point.getSelectedItem().toString());


    tempRemovedItem = spinner_start_point.getSelectedItem().toString();
    adapter_end.notifyDataSetChanged();
    spinner_end_point.setAdapter(adapter_end);




    }*/


         if(parent.getId() == spinner_end_point.getId())
        {

            switch (position) {
                case 0:
                    id_end_point=1;
                    break;
                case 1:
                    id_end_point=2;
                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;
                case 2:

                    id_end_point=3;
                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;
                case 3:
                    id_end_point=4;
                    break;
                case 4:
                    id_end_point=5;
                    break;
                case 5:
                    id_end_point=6;
                    break;
                case 6:
                    id_end_point=7;
                    break;
                case 7:
                    id_end_point=8;
                    break;
                case 8:
                    id_end_point=9;
                    break;
                case 9:
                    id_end_point=10;
                    break;
                case 10:
                    id_end_point=11;
                    break;
                case 11:
                    id_end_point=12;
                    break;
                case 12:
                    id_end_point=13;
                    break;
                case 13:
                    id_end_point=14;
                    break;
                case 14:
                    id_end_point=15;
                    break;
                case 15:
                    id_end_point=16;
                    break;
                case 16:
                    id_end_point=17;
                    break;

                case 17:
                    id_end_point=18;
                    break;

                case 18:
                    id_end_point=19;
                    break;
                case 19:
                    id_end_point=20;
                    break;
                case 20:
                    id_end_point=21;
                    break;
                case 21:
                    id_end_point=22;
                    break;
                case 22:
                    id_end_point=23;
                    break;
                case 23:
                    id_end_point=24;
                    break;
                case 24:
                    id_end_point=25;
                    break;
                case 25:
                    id_end_point=26;
                    break;
                case 26:
                    id_end_point=27;
                    break;
                case 27:
                    id_end_point=28;
                    break;

            }
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }




}