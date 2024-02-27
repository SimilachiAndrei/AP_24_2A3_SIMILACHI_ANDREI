public class Lab1 {
    public static void main(String[] args) {
        compulsory();
        String[] arg = {args[0],args[1],args[2]};
        homework(arg);
        /*int arg = Integer.parseInt(args[0]);
        bonus(arg);*/
    }
    public static void compulsory() {
        //compulsory
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n=n*3;
        n=n+ Integer.parseInt("10101",2);
        n+= Integer.parseInt("FF",16);
        n*=6;
        int result = 0;
        while( n > 0) {
            result = n % 10 + result;
            n = n / 10;
        }

        while(result > 9)
        {
            int aux=result;
            result=0;
            while(aux > 0)
            {
                result = result + aux %10;
                aux/=10;
            }
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
    public static void homework(String[] args)
    {
        long startTime = System.currentTimeMillis();
        if(args.length < 3)
        {
            System.out.print("Usage : the program needs two arguments");
            System.exit(0);
        }
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);
            for(int i=a;i<=b;i++)
            {
                int aux = i;
                if(i==k) System.out.println(i);
                else while(aux > 9) {
                    int sum = 0;
                    while (aux != 0) {
                        int pow = aux % 10;
                        sum = sum + pow * pow;
                        aux /= 10;
                    }
                    aux = sum;
                    if(aux==k)
                        System.out.println(i);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid integer.");
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");
    }

    public static int findCycles(int[][] matrix, int node, int len, int source, boolean[] visited) {
        if (node == source && visited[node])
        {
            return 1;
        }
        if (visited[node]) return 0;
        visited[node] = true;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (matrix[node][i] == 1) {
                count += findCycles(matrix, i, len, source, visited);
            }
        }
        visited[node] = false;
        return count;
    }

    public static void bonus(int arg)
    {
      if(arg<4){
          System.out.println("The number should be greater than 3");
          System.exit(0);
      }
      int[][] matrix = new int[arg][arg];
      for( int i=0;i<arg;i++)
      {
          for(int j=0;j<arg;j++)
          {
              if(i==0&&j!=0)matrix[i][j]=1;
              else if(j==i+1 || j==i-1)
              {
                  matrix[i][j]=1;
              }
              else matrix[i][j]=0;
          }
      }
        for( int i=0;i<arg;i++)
        {
            for(int j=0;j<arg;j++)
            {
              System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        int nrOfCicles = arg * arg - 3 * arg + 3;
        System.out.println("Correct number of cycles: " + nrOfCicles);
        int nrC = 0;
        int totalCycles = 0;
        boolean[][] cycles = new boolean[arg][arg];
        for (int source = 0; source < arg; source++) {
            boolean[] visited = new boolean[arg];
            totalCycles += findCycles(matrix, source, arg, source, visited);
        }
        System.out.println("Total number of cycles found: " + totalCycles);
    }
}