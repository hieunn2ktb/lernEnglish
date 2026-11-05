import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/** Generates Android launcher assets from the project icon source. */
public final class GenerateLauncherIcons {
    private static final int INDIGO = 0xFF24226B;

    public static void main(String[] args) throws Exception {
        File root = new File(args.length == 0 ? "." : args[0]);
        BufferedImage source = ImageIO.read(new File(root, "app/src/main/icon-source.png"));

        writeLegacy(root, source, "mipmap-mdpi", 48);
        writeLegacy(root, source, "mipmap-hdpi", 72);
        writeLegacy(root, source, "mipmap-xhdpi", 96);
        writeLegacy(root, source, "mipmap-xxhdpi", 144);
        writeLegacy(root, source, "mipmap-xxxhdpi", 192);

        File drawable = new File(root, "app/src/main/res/drawable-nodpi");
        drawable.mkdirs();
        BufferedImage foreground = canvas(432);
        drawContained(foreground, source, 0.82);
        ImageIO.write(foreground, "png", new File(drawable, "app_icon_foreground.png"));
    }

    private static void writeLegacy(File root, BufferedImage source, String bucket, int size)
            throws Exception {
        File directory = new File(root, "app/src/main/res/" + bucket);
        directory.mkdirs();
        BufferedImage icon = canvas(size);
        Graphics2D graphics = icon.createGraphics();
        quality(graphics);
        graphics.setColor(new Color(INDIGO, true));
        graphics.fill(new RoundRectangle2D.Double(0, 0, size, size, size * 0.22, size * 0.22));
        graphics.dispose();
        drawContained(icon, source, 0.88);
        ImageIO.write(icon, "png", new File(directory, "app_icon.png"));
        ImageIO.write(icon, "png", new File(directory, "app_icon_round.png"));
    }

    private static BufferedImage canvas(int size) {
        return new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    }

    private static void drawContained(BufferedImage target, BufferedImage source, double scale) {
        Graphics2D graphics = target.createGraphics();
        quality(graphics);
        graphics.setComposite(AlphaComposite.SrcOver);
        double available = target.getWidth() * scale;
        double ratio = Math.min(available / source.getWidth(), available / source.getHeight());
        int width = (int) Math.round(source.getWidth() * ratio);
        int height = (int) Math.round(source.getHeight() * ratio);
        int left = (target.getWidth() - width) / 2;
        int top = (target.getHeight() - height) / 2;
        graphics.drawImage(source, left, top, width, height, null);
        graphics.dispose();
    }

    private static void quality(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
    }
}
