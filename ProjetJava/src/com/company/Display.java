package com.company;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String titrefenetre;
    private int hauteur,largeur; //en pixels

    public Display(JFrame frame, String titrefenetre, int hauteur, int largeur) {
        this.frame = frame;
        this.titrefenetre = titrefenetre;
        this.hauteur = hauteur;
        this.largeur = largeur;

        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(titrefenetre);
        frame.setSize(largeur, hauteur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(largeur, hauteur));
        canvas.setMaximumSize(new Dimension(largeur, hauteur));
        canvas.setMinimumSize(new Dimension(largeur, hauteur));

        frame.add(canvas);
        frame.pack();
    }
}
