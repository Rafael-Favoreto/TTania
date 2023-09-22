import java.util.Arrays;
import java.util.Random;

public class Main {
    static int tamanho = 50000; // Aqui se decide o tamanho do vetor, para a pesquisa alteramos o tamanho do mesmo de acordo com nossa necessidade em relação a coleta de dados

    public static void main(String[] args) {
        // Inicializa os arrays e um objeto Random
        int[] vetBubble = new int[tamanho];
        int[] vetMerge = new int[tamanho];
        int[] vetSelect = new int[tamanho];
        int[] vetInsert = new int[tamanho];
        Random random = new Random();

        // Preenche os arrays com números aleatórios até o o tamanho limite do array
        for (int i = 0; i < tamanho; i++) {
            int num = random.nextInt(tamanho) + 1;
            vetBubble[i] = num;
            vetMerge[i] = num;
            vetSelect[i] = num;
            vetInsert[i] = num;
        }

        // Executa e mede o tempo do Bubble Sort
        System.out.println("Bubble Sort:");
        long tempoBubble = bubbleSort(vetBubble.clone());
        System.out.println("Tempo total de execução do Bubble Sort: " + tempoBubble + " nanossegundos");

        // Executa e mede o tempo do Merge Sort
        System.out.println("\nMerge Sort:");
        long tempoMerge = mergeSort(vetMerge.clone(), 0, tamanho - 1);
        System.out.println("Tempo total de execução do Merge Sort: " + tempoMerge + " nanossegundos");

        // Executa e mede o tempo do Selection Sort
        System.out.println("\nSelection Sort:");
        long tempoSelect = selectionSort(vetSelect.clone());
        System.out.println("Tempo total de execução do Selection Sort: " + tempoSelect + " nanossegundos");

        // Executa e mede o tempo do Insertion Sort
        System.out.println("\nInsertion Sort:");
        long tempoInsert = insertionSort(vetInsert.clone());
        System.out.println("Tempo total de execução do Insertion Sort: " + tempoInsert + " nanossegundos");
    }

    // Bubble Sort
    public static long bubbleSort(int[] arr) {
        int n = arr.length;
        boolean troca;
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            troca = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca os elementos caso estiverem deserganizados
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    troca = true;
                }
            }

            if (!troca) {
                break;
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Merge Sort
    public static long mergeSort(int[] arr, int esquerda, int direita) {
        long startTime = System.nanoTime();

        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            // Divide o array em duas metades e ordena recursivamente
            mergeSort(arr, esquerda, meio);
            mergeSort(arr, meio + 1, direita);
            // Combina as duas metades ordenadas
            merge(arr, esquerda, meio, direita);
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Função auxiliar para combinar dois subarrays ordenados
    public static void merge(int[] arr, int esquerda, int meio, int direita) {
        int[] temp = Arrays.copyOfRange(arr, esquerda, direita + 1);
        int i = 0, j = meio - esquerda + 1, k = esquerda;

        while (i < meio - esquerda + 1 && j < direita - esquerda + 1) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i < meio - esquerda + 1) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }

    // Selection Sort
    public static long selectionSort(int[] arr) {
        int n = arr.length;
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }

            // Troca o elemento mínimo com o elemento atual
            int temp = arr[indiceMinimo];
            arr[indiceMinimo] = arr[i];
            arr[i] = temp;
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Insertion Sort
    public static long insertionSort(int[] arr) {
        int n = arr.length;
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            int chave = arr[i];
            int j = i - 1;
            // Move os elementos maiores que a chave para a direita
            while (j >= 0 && arr[j] > chave) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = chave;
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}

