import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.imageio.ImageIO;


public class PDI {

    private final String imagePath;

    PDI(String imagePath) {
        this.imagePath = imagePath;
    }

    public static void main(String[] args) {
        String imagePath = this.imagePath;
        PDI pdi = new PDI(imagePath);
        try {
            BufferedImage image = pdi.readImage();
            Vector<int[][]> imageConvertedToMatrix = pdi.convertToPixelsMatrix(image);
            pdi.saveImage(imageConvertedToMatrix);
            pdi.saveRedImage(imageConvertedToMatrix);
            pdi.saveBlueImage(imageConvertedToMatrix);
            pdi.saveGreenImage(imageConvertedToMatrix);
            pdi.saveGrayImage(imageConvertedToMatrix);
            pdi.saveDarkGrayImage(imageConvertedToMatrix);
            pdi.saveLightGrayImage(imageConvertedToMatrix);
            pdi.saveBlackAndWhiteImage(imageConvertedToMatrix);
            pdi.saveNegativeImage(imageConvertedToMatrix);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading image: " + e.getMessage());
        }
    }

    private BufferedImage readImage() throws IOException {
        File imageFile = new File(this.imagePath);
        BufferedImage image = ImageIO.read(imageFile);
        if (image != null) {
            return image;
        }
        throw new IOException("Failed to read the image");
    }

    private Vector<int[][]> convertToPixelsMatrix(BufferedImage image){
        int rows = image.getHeight();
        int columns = image.getWidth();
        int[][] redMatrix = new int[rows][columns];
        int[][] greenMatrix = new int[rows][columns];
        int[][] blueMatrix = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                int[] rgb = this.getPixel(image, j, i);
                redMatrix[i][j] = rgb[0];
                greenMatrix[i][j] = rgb[1];
                blueMatrix[i][j] = rgb[2];
            }
        }

        Vector<int[][]> rgb = new Vector<>();
        rgb.add(redMatrix);
        rgb.add(greenMatrix);
        rgb.add(blueMatrix);
        return rgb;
    }

    private int[] getPixel(BufferedImage image, int xCoord, int yCoord){
        int pointRGBfromXandY = image.getRGB(xCoord, yCoord); // 32bits -> alfa, red, green, blue.
        return new int[]{
                (pointRGBfromXandY>>16) & 0xff,
                (pointRGBfromXandY>>8)  & 0xff,
                (pointRGBfromXandY) & 0xff
        };
    }

    private void saveRedImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalPixels[position] = rgb.get(0)[i][j];
                position += 3;
            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_red.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveGreenImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalPixels[position + 1] = rgb.get(1)[i][j];
                position += 3;
            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_green.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveBlueImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalPixels[position + 2] = rgb.get(2)[i][j];
                position += 3;
            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_blue.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveGrayImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalPixels[position] = ((rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3) ;
                totalPixels[position + 1] = ((rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3) ;
                totalPixels[position + 2] = ((rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3);
                position += 3;
            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_gray.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveDarkGrayImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalPixels[position] = ((rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3) / 3;
                totalPixels[position + 1] = ((rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3) / 3;
                totalPixels[position + 2] = ((rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3) / 3;
                position += 3;
            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_dark_gray.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveLightGrayImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int gray = (rgb.get(0)[i][j] + rgb.get(1)[i][j] + rgb.get(2)[i][j]) / 3;

                totalPixels[position] = gray + 30;
                totalPixels[position + 1] = gray + 30;
                totalPixels[position + 2] = gray + 30;

                position += 3;


            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_light_gray.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveBlackAndWhiteImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        int threshold= 0;//limiar
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = rgb.get(0)[i][j];
                int green = rgb.get(1)[i][j];
                int blue = rgb.get(2)[i][j];
                threshold += (red + green + blue) / 3;
            }
        }
        threshold /= (width * height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = rgb.get(0)[i][j];
                int green = rgb.get(1)[i][j];
                int blue = rgb.get(2)[i][j];
                int gray = (red + green + blue) / 3;
                if(gray < threshold){
                    totalPixels[position] = 0;//black
                    totalPixels[position + 1] = 0;
                    totalPixels[position + 2] = 0;
                }else{
                    totalPixels[position] = 255;
                    totalPixels[position + 1] = 255;//white
                    totalPixels[position + 2] = 255;
                }
                position += 3;

            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_bandw.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveNegativeImage(Vector<int[][]> rgb){
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = rgb.get(0)[i][j];
                int green = rgb.get(1)[i][j];
                int blue = rgb.get(2)[i][j];

                totalPixels[position] = 255 - red;
                totalPixels[position + 1] = 255 - green;
                totalPixels[position + 2] =  255 - blue;

                position += 3;

            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process_negative.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }

    }

    private void saveImage(Vector<int[][]> rgb) {
        int width = rgb.get(0)[0].length;
        int height = rgb.get(0).length;

        int[] totalPixels = new int[width * height * 3];
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = newImage.getRaster();
        int position = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalPixels[position] = rgb.get(0)[i][j];
                totalPixels[position + 1] = rgb.get(1)[i][j];
                totalPixels[position + 2] = rgb.get(2)[i][j];

                position += 3;
            }
        }
        raster.setPixels(0, 0, width, height, totalPixels);

        try {
            File outputfile = new File("process.png");
            ImageIO.write(newImage, "png", outputfile);
            System.out.println("Image has been saved.");
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }
    }
}
