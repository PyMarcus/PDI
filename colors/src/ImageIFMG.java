/*
 * Prática de Processamento Digital de Imagens
 * prof.  ngelo Magno de Jesus
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

public class ImageIFMG extends JFrame{
    private JDesktopPane theDesktop;
    private int[][] matR, matG, matB;

    JFileChooser fc  = new JFileChooser();
    String path = "";

    public void escalaCinza()
    {
        int width = matB[0].length;
        int height = matB.length;

        int[] pixels = new int[width * height * 3];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = img.getRaster();
        int n = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int gray = (matR[i][j] + matG[i][j] + matB[i][j]) / 3;
                pixels[n] = gray;
                pixels[n + 1] = gray;
                pixels[n + 2] = gray;
                n+=3;
            }
        }

        raster.setPixels(0, 0, width, height, pixels);

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(img));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }

    public void imagemBinaria()
    {
        int width = matB[0].length;
        int height = matB.length;

        int[] pixels = new int[width * height];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        WritableRaster raster = img.getRaster();
        int n = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int gray = (matR[i][j] + matG[i][j] + matB[i][j]) / 3;
                pixels[n] = gray >= 128?255:0;
                n+=1;
            }
        }

        raster.setPixels(0, 0, width, height, pixels);

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(img));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }

    public void imagemNegativa()
    {
        int width = matB[0].length;
        int height = matB.length;

        int[] pixels = new int[width * height * 3];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = img.getRaster();
        int n = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                pixels[n] = 255-matR[i][j];
                pixels[n + 1] = 255-matG[i][j];
                pixels[n + 2] = 255-matB[i][j];
                n+=3;
            }
        }

        raster.setPixels(0, 0, width, height, pixels);

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(img));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }

    public void corDominante()
    {
        HashMap<Integer, Integer> countColors = new HashMap<>();

        int width = matB[0].length;
        int height= matB.length;
        int[] pixels = new int[width * height*3];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = img.getRaster();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int color = img.getRGB(j, i);
                countColors.put(color, countColors.getOrDefault(color, 0) + 1);
            }
        }

        int maxColor = 0;
        int dominant = 0;
        for(Map.Entry<Integer, Integer> entry: countColors.entrySet()){
            int v = entry.getValue();
            System.out.println("V " + v);
            if(v > maxColor){
                maxColor = v;
                dominant = entry.getKey();
            }
        }
        int red = (dominant >> 16) & 0xFF;
        int green = (dominant >> 8) & 0xFF;
        int blue = dominant & 0xFF;
        System.out.println("Cor dominante: RGB(" + red + ", " + green + ", " + blue + ")");
        int n = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                pixels[n] = red;
                pixels[n + 1] = green;
                pixels[n + 2] = blue;
                n+=3;
            }
        }

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(img));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);

    }


    public void escalaCinzaEscuro()
    {
        int width = matB[0].length;
        int height = matB.length;

        int[] pixels = new int[width * height * 3];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = img.getRaster();
        int n = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int gray = (matR[i][j] + matG[i][j] + matB[i][j]) / 3;
                pixels[n] = gray / 3;
                pixels[n + 1] = gray /3;
                pixels[n + 2] = gray /3;
                n+=3;
            }
        }

        raster.setPixels(0, 0, width, height, pixels);

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(img));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }


    public void escalaCinzaClaro()
    {
        int width = matB[0].length;
        int height = matB.length;

        int[] pixels = new int[width * height * 3];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = img.getRaster();
        int n = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int gray = (matR[i][j] + matG[i][j] + matB[i][j]) / 3;
                pixels[n] = gray + 5;
                pixels[n + 1] = gray + 5;
                pixels[n + 2] = gray + 5;
                n+=3;
            }
        }

        raster.setPixels(0, 0, width, height, pixels);

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(img));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }


    public ImageIFMG(){
        super("PhotoIFMG");
        JMenuBar bar = new JMenuBar();
        JMenu addMenu = new JMenu("Abrir");
        JMenuItem fileItem = new JMenuItem("Abir uma imagem de arquivo");
        JMenuItem newFrame = new JMenuItem("Internal Frame");


        addMenu.add(fileItem);
        addMenu.add(newFrame);
        bar.add(addMenu);

        JMenu addMenu2 = new JMenu("Processar");
        JMenuItem item1 = new JMenuItem("Escala de cinza");
        JMenuItem item2 = new JMenuItem("Imagem binária");
        JMenuItem item3 = new JMenuItem("Negativa");
        JMenuItem item4 = new JMenuItem("Cor dominante");
        JMenuItem item5 = new JMenuItem("Cinza escuro");
        JMenuItem item6 = new JMenuItem("Cinza claro");

        addMenu2.add(item1);
        addMenu2.add(item2);
        addMenu2.add(item3);
        addMenu2.add(item4);
        addMenu2.add(item5);
        addMenu2.add(item6);

        bar.add(addMenu2);

        setJMenuBar(bar);

        theDesktop = new JDesktopPane();
        getContentPane().add(theDesktop);

        newFrame.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JInternalFrame frame = new JInternalFrame("Exemplo", true,
                                true, true, true);
                        Container container = frame.getContentPane();
                        MyJPanel panel = new MyJPanel();
                        container.add(panel, BorderLayout.CENTER);

                        frame.pack();
                        theDesktop.add(frame);
                        frame.setVisible(true);


                    }
                }

        );

        //ler imagem
        fileItem.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = fc.showOpenDialog(null);
                        if(result == JFileChooser.CANCEL_OPTION){
                            return;
                        }
                        path = fc.getSelectedFile().getAbsolutePath();

                        JInternalFrame frame = new JInternalFrame("Exemplo", true,
                                true, true, true);
                        Container container = frame.getContentPane();
                        MyJPanel panel = new MyJPanel();
                        container.add(panel, BorderLayout.CENTER);

                        frame.pack();
                        theDesktop.add(frame);
                        frame.setVisible(true);
                    }
                }

        );

        item1.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<int[][]> rgbMat = getMatrixRGB();
                        matR = rgbMat.elementAt(0);
                        matG = rgbMat.elementAt(1);
                        matB = rgbMat.elementAt(2);

                        escalaCinza();

     				/*int[][] mat = rgbMat.elementAt(0);
     				int[][] mat2 = rgbMat.elementAt(1);
     				int[][] mat3 = rgbMat.elementAt(2);

     				geraImagem(mat3, mat2
     						, mat);*/
                    }
                }

        );

        item2.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<int[][]> rgbMat = getMatrixRGB();
                        matR = rgbMat.elementAt(0);

                        matG = rgbMat.elementAt(1);
                        matB = rgbMat.elementAt(2);

                        imagemBinaria();

                    }
                }

        );

        item3.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<int[][]> rgbMat = getMatrixRGB();
                        matR = rgbMat.elementAt(0);

                        matG = rgbMat.elementAt(1);
                        matB = rgbMat.elementAt(2);

                        imagemNegativa();
                    }
                }

        );

        item4.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<int[][]> rgbMat = getMatrixRGB();
                        matR = rgbMat.elementAt(0);

                        matG = rgbMat.elementAt(1);
                        matB = rgbMat.elementAt(2);

                        corDominante();
                    }
                }

        );

        item5.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<int[][]> rgbMat = getMatrixRGB();
                        matR = rgbMat.elementAt(0);

                        matG = rgbMat.elementAt(1);
                        matB = rgbMat.elementAt(2);

                        escalaCinzaEscuro();
                    }
                }

        );

        item6.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<int[][]> rgbMat = getMatrixRGB();
                        matR = rgbMat.elementAt(0);

                        matG = rgbMat.elementAt(1);
                        matB = rgbMat.elementAt(2);

                        escalaCinzaClaro();

                    }
                }

        );

        setSize(600, 400);
        setVisible(true);


    }

    //ler matrizes da imagem
    public Vector<int[][]> getMatrixRGB(){
        BufferedImage img;
        int[][] rmat = null;
        int[][] gmat = null;
        int[][] bmat = null;
        try {
            img = ImageIO.read(new File(path));

            int[][] pixelData = new int[img.getHeight() * img.getWidth()][3];
            rmat = new int[img.getHeight()][img.getWidth()];
            gmat = new int[img.getHeight()][img.getWidth()];
            bmat = new int[img.getHeight()][img.getWidth()];

            int counter = 0;
            for(int i = 0; i < img.getHeight(); i++){
                for(int j = 0; j < img.getWidth(); j++){
                    rmat[i][j] = getPixelData(img, j, i)[0];
                    gmat[i][j] = getPixelData(img, j, i)[1];
                    bmat[i][j] = getPixelData(img, j, i)[2];

                    /*for(int k = 0; k < rgb.length; k++){
                        pixelData[counter][k] = rgb[k];
                    }*/

                    counter++;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        Vector<int[][]> rgb = new Vector<int[][]>();
        rgb.add(rmat);
        rgb.add(gmat);
        rgb.add(bmat);

        return rgb;

    }
    //cria imagem da matriz
    private void geraImagem(int matrix1[][], int matrix2[][], int matrix3[][])
    {
        int[] pixels = new int[matrix1.length * matrix1[0].length*3];
        BufferedImage image = new BufferedImage(matrix1[0].length, matrix1.length, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        int pos =0;
        for(int i =0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                pixels[pos] = matrix1[i][j];
                pixels[pos+1] = matrix2[i][j];
                pixels[pos+2] = matrix3[i][j];
                pos+=3;
            }
        }
        raster.setPixels(0, 0, matrix1[0].length, matrix1.length, pixels);

        //Abre Janela da imagem
        JInternalFrame frame = new JInternalFrame("Processada", true,
                true, true, true);
        Container container = frame.getContentPane();
        MyJPanel panel = new MyJPanel();
        panel.setImageIcon(new ImageIcon(image));
        container.add(panel, BorderLayout.CENTER);

        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);

    }

    public int[][] obterMatrizVermelha(){
        return matR;
    }

    public int[][] obterMatrizVerde(){
        return matG;
    }

    public int[][] obterMatrizAzul(){
        return matB;
    }

    private static int[] getPixelData(BufferedImage img, int x, int y) {
        int argb = img.getRGB(x, y);

        int rgb[] = new int[] {
                (argb >> 16) & 0xff, //red
                (argb >>  8) & 0xff, //green
                (argb      ) & 0xff  //blue
        };

        return rgb;
    }

    class MyJPanel extends JPanel{
        private ImageIcon imageIcon;

        public MyJPanel(){
            imageIcon = new ImageIcon(path);
        }

        public void setImageIcon(ImageIcon i){
            imageIcon = i;
        }

        public void paintComponent(Graphics g){
            super.paintComponents(g);
            imageIcon.paintIcon(this, g, 0, 0);
        }

        public Dimension getPreferredSize(){
            return new Dimension(imageIcon.getIconWidth(),
                    imageIcon.getIconHeight());
        }

    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        ImageIFMG app = new ImageIFMG();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}


