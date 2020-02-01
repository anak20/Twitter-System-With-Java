package tugas2;

/**
 *
 * @author Ersya Nadia Candra
 */
import java.util.Scanner;

class Node { //membuat class node yang bisa menyimpan angka dan String

    String nama;
    Node next;
    int angka;

    public Node(String nama) {
        this.nama = nama;
        this.next = null;
    }

    public Node(int nama) {
        this.angka = nama;
        this.next = null;
    }

}

// class Linked list saya membuat method yang dibutuhkan saja yang nantinya menyimpan data sebagai pengganti penyimpanan array list pada program saya
class Linkedlist { //sama seperti tugas 1

    Node pertama, terakhir;
    int size = 0;

    public Linkedlist() {
        pertama = terakhir = null;
    }

    public boolean ApakahKosong() {
        return pertama == null;
    }

    public void TambahBelakang(String data) {
        Node temp = new Node(data);
        if (ApakahKosong()) {
            pertama = terakhir = temp;

        } else {
            terakhir.next = temp;
            terakhir = temp;
        }
        size++;
    }

    public void TambahBelakang(int data) {
        Node temp = new Node(data);
        if (ApakahKosong()) {
            pertama = terakhir = temp;

        } else {
            terakhir.next = temp;
            terakhir = temp;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public void HapusBelakang() {
        if (!ApakahKosong()) {
            Node temp = pertama;
            while (temp.next != terakhir) {
                temp = temp.next;
            }
            temp.next = null;
            terakhir = temp;
            size--;
        }

    }

    //method tambah size untuk menambah ukuran array menjadi bertambah 1 untuk array bertipe String
    private String[] TambahSize(String[] array, int sizebaru) {
        String[] temp = new String[sizebaru];
        if (array.length < sizebaru) {
            sizebaru = array.length;
        }
        System.arraycopy(array, 0, temp, 0, sizebaru);
        return temp;
    }

    //method tambah size untuk menambah ukuran array menjadi bertambah 1 untuk array bertipe integer
    private int[] TambahSize(int[] array, int sizebaru) {
        int[] temp = new int[sizebaru];
        if (array.length < sizebaru) {
            sizebaru = array.length;
        }
        System.arraycopy(array, 0, temp, 0, sizebaru);
        return temp;
    }

    // method ini untuk mengurutkan data dalam array bertype string yang diurutkan secara ascending atau dari kecil ke besar mengimplementasikan algoritma bubblesort
    public String[] Urutkan(String[] a) {
        String temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i].compareTo(a[j]) > 0) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

    //method yang saya buat untuk mencari minat tertinggi dengan mengambil semua macam minat yang berbeda dalam suatu gruop yang dilanjutkan dengan diurutkan secara ascending, kemudian
    //membandingkan nya dengan satu-satu minat dari setiap member atau vertex yang nantinya akan dicounter dengan variabel jumlah untuk setiap minatnya
    // dan disimpan kedalam string bertipe integer bernama b yang kemudian nanti dicari yang paling besar nilai nya, dan kemudian nilai terbesar dibandingkan dengan hasil jumlah dari 
    //masing-masing minat jika ada yang sama nilainya akan di jadikan dalam satu string. return value dari method ini adalah bertype String.
    String minatTertinggi() {
        String a[] = new String[3]; //untuk menyimpan minat
        int b[] = new int[3]; //untuk menyimpan jumlah kemunculan minat
        Node temp = pertama;
        int pivot = 0; // sebagai variabel berjalan untuk index setiap kali penambahan minat yang berbeda.
        String minat[] = temp.nama.split(" ");
        a[pivot++] = minat[1];
        a[pivot++] = minat[2];
        a[pivot++] = minat[3];
        boolean g;
        while (temp != null) { // perulangan untuk mencari macam minat yang berbeda-beda dalam satu group
            String c[] = temp.nama.split(" ");
            g = true;
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < pivot; j++) {
                    if (a[j].equals(c[i])) {
                        g = false;
                        break;
                    } else {
                        g = true;
                    }
                }

                if (g) {

                    if (pivot == a.length) {
                        a = TambahSize(a, a.length + 1);
                        b = TambahSize(b, b.length + 1);
                    }
                    a[pivot++] = c[i];
                }

            }
            temp = temp.next;
        }

        a = Urutkan(a); // mengurutkan group dari yang kecil ke besar atau dari A-Z

        String gabung = ""; // untuk menggabungkan minat yang memiliki nilai sama 
        temp = pertama;
        while (temp != null) { // perulangan untuk mencari jumlah kemunculan tiap2 minat yang sudah di urutkan sebelumnya yang disimpan dalam array b yang panjang nya sama dengan array a , sebenarnya sih array 2 dimensi tetapi saya pecah agar mudah
            String d[] = temp.nama.split(" ");
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (a[j].equals(d[i])) {
                        b[j] = b[j] + 1;
                    }
                }

            }
            temp = temp.next;
        }

        int max = b[0];
        for (int i = 0; i < b.length; i++) {  //mencari jumlah kemunculan minat tertinggi yang disimpan dalam variabel max
            if (max < b[i]) {
                max = b[i];
            }
        }
        int jumlah = 0; //iterasi yang nantinya bertambah sejumlah minat yang jumlah kemunculannya sama dengan max
        for (int i = 0; i < b.length; i++) {  //mencari minat yang nilainya sama dengan maximal atau max, ketika sama maka akan digabung di dalam variabel String gabung ketika iterasi jumlah lebih dari 2 maka akan dipisahkan dengan koma
            if (max == b[i]) {// artinya ketika lebih dari 1 yang minatnya sama maka akan dipisahkan dengan koma
                jumlah++;
                if (jumlah > 1) {
                    gabung += "," + a[i];
                } else {
                    gabung += a[i];
                }

            }
        }

        return gabung; //mengembalikan variabel gabung bertype String, mereturn minat tertinggi dari group ini  
    }
}

class graph {

    private int numVertex; //untuk iterasi mengikuti banyaknya vertex yang diinsertkan
    private int numEdges; //untuk iterasi mengikuti banyaknya connect yang diinsertkan
    private String[] Vertex; //menyimpan vertex disini saya menyimpan nama beserta minatnya dalam array Vertex
    private int[] index; // menyimpan index group dari setiap vertex, termasuk dalam group berapa 
    private boolean[][] Edges; // menyimpan connection atau Edges antar Vertex karena saya menggunakan algoritma Adjacency
    int numgroup = 0;

    public graph(int jumlah) { //contructor class graph
        Vertex = new String[2];
        Edges = new boolean[2][2];
        index = new int[2];
        for (int i = 0; i < Edges.length; i++) { //inisialisasi semua coneection false, nanti akan menjadi true ketika ada conection
            for (int j = i + 1; j < Edges[i].length; j++) {
                Edges[i][j] = false;
            }
        }
    }

    public String[] getVertex() { //method getter dari variabel local array vertex
        return Vertex;
    }

    public int index(String vertex) { //method index untuk mengetahui posisi index dari vertex berdasarkan nama, jika tidak ditemukan akan mengembalikan -1 jika ditemukan akan mereturn value posisi indexnya
        for (int i = 0; i < numVertex; i++) {
            String[] a = Vertex[i].split(" ");
            if (vertex.equals(a[0])) {
                return i;
            }
        }
        return -1;
    }

    //method tambah size untuk menambah ukuran array menjadi bertambah 1 untuk array bertipe string
    public String[] TambahSize(String[] array, int sizebaru) {
        String[] temp = new String[sizebaru];
        if (array.length < sizebaru) {
            sizebaru = array.length;
        }
        System.arraycopy(array, 0, temp, 0, sizebaru);
        return temp;
    }

    //method tambah size untuk menambah ukuran array menjadi bertambah 1 untuk array bertipe integer
    public int[] TambahSize(int[] array, int sizebaru) {
        int[] temp = new int[sizebaru];
        if (array.length < sizebaru) {
            sizebaru = array.length;
        }
        System.arraycopy(array, 0, temp, 0, sizebaru);
        return temp;
    }

    //method tambah size untuk menambah ukuran array menjadi bertambah 1 untuk array bertipe boolean dua dimensi
    public boolean[][] TambahSize(boolean[][] array, int sizebaru) {
        boolean[][] temp = new boolean[sizebaru][sizebaru];
        if (array.length < sizebaru) {
            sizebaru = array.length;
        }
        for (int i = 0; i < sizebaru; i++) {
            System.arraycopy(array[i], 0, temp[i], 0, sizebaru);
        }
        return temp;
    }

    public void addVertex(String vertex) { // method untuk menambahkan vertex baru jika panjang vertex sama dengan numvertex maka size dari array vertex, edges dan index akan ditambah satu
        if (Vertex.length == numVertex) {
            Vertex = TambahSize(Vertex, numVertex + 1);
            Edges = TambahSize(Edges, numVertex + 1);
            index = TambahSize(index, numVertex + 1);
        }
        Vertex[numVertex++] = vertex;
        index[Vertex.length - 1] = 0;
        String[] pecah = vertex.split(" ");
        System.out.println(pecah[0] + " " + "inserted");

    }

    public void addMember(String vertex) { //sama dengan addVertex hanya saja tidak menampilkan nama dari username yang berhasil ditambahkan, jadi ini digunakan untuk inisialisasi member awal
       if (Vertex.length == numVertex) {
            Vertex = TambahSize(Vertex, numVertex + 1);
            Edges = TambahSize(Edges, numVertex + 1);
            index = TambahSize(index, numVertex + 1);
        }
        Vertex[numVertex++] = vertex;
        index[Vertex.length - 1] = 0;
    }

    public void connect(String nama1, String nama2) { //connection untuk menghubungkan atau follow antar username satu dengan yang lain dimana saya juga menambahkan kodingan untuk menginialisasi kedua username tersebut dalam group berapa
        int i = index(nama1);  //memfollow                         // sesuai iterasi numgroup
        int j = index(nama2);  //difollow
        addEdge(i, j);

        if (index[i] == 0 && index[j] == 0) { //ketika sama2 tidak punya group atau masih 0 maka akan berubah menjadi group 1
            numgroup++;
            index[i] = numgroup;
            index[j] = numgroup;
        }

        if (index[i] != 0 && index[j] == 0) { //jika yang difollow tidak punya group atau 0 dan yang memfollow punya group maka mengikuti yang group yang memfollow

            index[j] = index[i];

        }

        if (index[i] == 0 && index[j] != 0) { //jika yang memfollow tidak punya group atau 0 dan yang difollow punya group maka mengikuti yang group yang difollow
            index[i] = index[j];

        }

    }

    void addEdge(int i, int j) { //method yang dipanggil dalam method connection, dengan menginisialisasi menjadi true dalam adjacency matrix posisi i,j
        this.Edges[i][j] = true;
    }

    Linkedlist diFollow(String b) { //method yang mengembalikan linked list yang menyimpan vertex yang difollow username tertentu sesuai parameter 
        String[] h = b.split(" ");
        int i = index(h[0]);
        Linkedlist a = new Linkedlist();
        for (int j = 0; j < Edges.length; j++) {
            if (this.Edges[i][j]) {
                a.TambahBelakang(Vertex[j]);
            }

        }
        return a;
    }

    Linkedlist memFollow(String b) { //method yang mengembalikan linked list yang menyimpan vertex  yang memfollow username tertentu sesuai parameter 
        String[] h = b.split(" ");
        int i = index(h[0]);
        Linkedlist a = new Linkedlist();
        for (int j = 0; j < Vertex.length; j++) {
            if (this.Edges[j][i]) {
                a.TambahBelakang(Vertex[j]);
            }
        }
        return a;
    }

    public int group() { //method menghitung jumlah group dalam twitter yaitu mengembalikan numgroup ditambah dengan single yang artinya semua username yang masih tidak punya group atau berdiri sendiri yang memiliki index 0
        int single = 0;
        for (int i = 0; i < index.length; i++) {
            if (index[i] == 0) {
                single++;
            }
        }

        return numgroup + single;

    }

    public String[] Urutkan(String[] a) { //mengurutkan data dalam array bertype String dari A ke Z mengimplementasikan algoritma bubllesort
        String temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; j < a.length; j++) //proses pertukaran data 
            {
                if (a[i].compareTo(a[j]) > 0) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

    public void grouptopic() { //method yang saya buat untuk menampilkan grouptopic dari masing-masing group , dengan memanggil method minat tertinggi diclass linked list diatas yang sebelumnya data dimasukkan dikelompokkan berdarkan index group yang dimiliki user tersebut
        String array[] = new String[group()];
        int in = 0;
        for (int i = 1; i <= group(); i++) {
            Linkedlist a = new Linkedlist();
            for (int j = 0; j < index.length; j++) {
                if (index[j] == i) {
                    a.TambahBelakang(Vertex[j]);
                }
            }
            array[in] = a.minatTertinggi();
            in++;
        }

        array = Urutkan(array);
        for (String array1 : array) {
            System.out.println(array1);
        }

    }

    public int minrt(String dari, String tujuan) {//method untuk mengetahui jarak terpendek dari user satu ke user yang lain, seharusnya bisa menggunakan algoritma penelusuran BFS maupun DFS, tetapi karena saya menggunakan adjacency matrix dan tidak diperbolehkannya arraylist 
        // saya membuat algoritma baru yang melakukan penelusuran berdasarkan index dalam adjacency matrix konsepnya hampir sama yaitu melakukan penelusuran ke dalam, dan yan sudah divisit tidak akan divisit lagi kecuali 2 arah
        int d = index(dari);                        // kemudian hasil penelusuran disimpan dalam array list, dan dicari yang paling minimum
        int t = index(tujuan);
        int go = d;
        boolean visited[][] = new boolean[Edges.length][Edges.length];
        int num = 0;
        int visiting[][] = new int[Edges.length][Edges.length];
        if (Edges[d][t]) {
            return 0;
        }
        Linkedlist num2 = new Linkedlist();
        for (int i = 0; i < Edges[go].length; i++) {
            if (Edges[go][i] && !visited[go][i]) {
                num++;
                visited[go][i] = true;
                if (Edges[i][go] && visited[i][go]) {
                    num -= 2;
                }
                if (Edges[i][t]) {
                    num2.TambahBelakang(num);
                    num = 0;
                    go = d;
                    i = -1;
                } else {
                    go = i;
                    i = -1;
                }
            } else if (Edges[go][i] && visited[go][i] && visiting[go][i] == 0) {
                visiting[go][i]++;
                go = d;
                i = -1;
            }
        }
        if (num2.pertama == null) {
            return -1;
        }
        int min = num2.pertama.angka;
        Node temp = num2.pertama;
        while (temp != null) {
            if (min > temp.angka) {
                min = temp.angka;
            }
            temp = temp.next;
        }

        return min;

    }

}

public class Twitter {

    graph twitter;   //inisialisasi variabel twitter yan merupakan object dari kelas Graph
    int numgrup = 0;

    public Twitter(int vertex) {
        twitter = new graph(vertex);    //menginisialisasi banyaknya vertex dalam graph
    }

    public void member(String a) {   //memasukkan member atau vertex awal dengan menggil method addMember dari class graph
        twitter.addMember(a);
    }

    public void insert(String a) { //memasukkan vertex yang baru diinsertkan dengan menggil method addVertex dari class graph
        twitter.addVertex(a);
    }

    public void connect(String a, String b) { //connection atau follow ketika a memfollow b maka memanggil method connect dari class graph dan adjacency matrix ke a,b akan bernilai true dan diinisialisasi sebagai grup mengikuti salah satu jika salah satu sudah punya group dan membuat goup baru jika sama2 belum punya group
        twitter.connect(a, b);
    }

    public String mostfollowed() { //mencari followed tertinggi disini saya menggunakan method memfollow dari class twitter yang mereturn Linkedlist yang berisi nama beserta minat yang menfollow username tertentu
        String[] a = twitter.getVertex(); // didalam class linkedlist terdapat size yang menghitung jumlahnode dalam linkedlist tersebut, kemudian dari situlah saya mengambil size dari linkedlist hasil dari method memfollow masing2 vertex atau member
        String pertama = a[0];                  // kemudian dibandingkan dicari maximumnya, kemudian di bandingkan lagi dicari yang sama dengan nilai maximum dan mereturn nama2 yang follower nya tertinggi
        for (int i = 1; i < a.length; i++) {        //yang dipisahkan dengan koma dan sebelumnya sudah diurutkan
            if (twitter.memFollow(pertama).size < twitter.memFollow(a[i]).size) {
                pertama = a[i];
            }
        }

        int max = twitter.memFollow(pertama).size;
        String[] gabung = new String[1];
        int o = 0;
        for (String a1 : a) {
            if (max == twitter.memFollow(a1).size) {
                String[] followed = a1.split(" ");
                if (o == gabung.length) {
                    gabung = twitter.TambahSize(gabung, gabung.length + 1);
                }
                gabung[o] = followed[0];
                o++;
            }
        }
        String asr = "";
        gabung = twitter.Urutkan(gabung);
        for (int i = 0; i < gabung.length; i++) {
            if (i > 0) {
                asr += "," + gabung[i];
            } else {
                asr += gabung[i];
            }

        }

        return asr;
    }

    public int grouping() { // memanggil method group dari class grap
        return twitter.group();
    }

    public void Grouptopic() {
        twitter.grouptopic();

    }

    public void minrt(String from, String tujuan) { //memanggil method minrt dari class graph
        System.out.println(twitter.minrt(from, tujuan));

    }

    void execute(Linkedlist a) { //untuk menjalankan semua perintah yang diinputkan
        Node temp = a.pertama;
        while (temp != null) { // iterasi berhenti sampai temp = null
            String[] arr = temp.nama.split(" ");
            switch (arr[0]) {
                case "mostfollowed": // jika arr[0] atau perintahnya sama dengan mostfollowed 
                    System.out.println(mostfollowed()); //menampilkan hasil dari method mostfollowed
                    break;
                case "insert":  // jika arr[0] atau perintahnya sama dengan insert
                    insert(arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]); // arr[1] adalah username, arr[2] = minat1, arr[3] = minat2 , arr[4] = minat3
                    break;
                case "numgroup": // jika arr[0] atau perintahnya sama dengan numgroup
                    System.out.println(grouping());
                    break;
                case "connect": // jika arr[0] atau perintahnya sama dengan connect
                    connect(arr[1], arr[2]);
                    System.out.println("connect " + arr[1] + " " + arr[2] + " success"); //arr[1] yang memfollow arr[2]
                    break;
                case "grouptopic": // jika arr[0] atau perintahnya sama dengan grouptopic
                    Grouptopic();
                    break;
                case "minrt": // jika arr[0] atau perintahnya sama dengan minrt
                    minrt(arr[1], arr[2]); // jarak terpendek arr[1] ke arr[2] harus melewati berapa user
                    break;

                default:
                    break;

            }
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            //jumlah vertex yang menjadi member
            int data = in.nextInt();

            //jumlah connectsi member
            int data1 = in.nextInt();

            //deklarasi object dari class Twitter  
            Twitter g = new Twitter(data);
            in.nextLine();
            //input member atau vertex awal
            for (int i = 0; i < data; i++) {
                g.member(in.nextLine());
            }
            //input edges atau connection awal
            for (int i = 0; i < data1; i++) {
                g.connect(in.next(), in.next());
            }

            //Deklarasi object baru dari class linkedlist untuk menyimpan perintah yang nantinya akan di eksekusi, berhenti sampai end of fie
            Linkedlist perintah = new Linkedlist();
            String Line;
            in.nextLine();
            while (true) {
                Line = in.nextLine();
                if (Line.isEmpty()) { //sampai ada line kosong atu tidak ada inputan lagi
                    break;
                } else {
                    perintah.TambahBelakang(Line); //menyimpan semua perintah
                }
            }
            g.execute(perintah); //setelah semua perintah habis maka akan di eksekusi dalam method execute dari class Twitter
        } catch (Exception e) {
                System.out.println("WRONG FORMAT");
        }
    }
}
