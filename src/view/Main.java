package view;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema Processos Seletivos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,400);
            frame.setLayout(new GridLayout(0,1));

            JButton btnDisc = new JButton("Gerenciar Disciplinas");
            btnDisc.addActionListener(e -> {
                try { new DisciplinaForm(frame).setVisible(true); } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(frame, ex.getMessage()); }
            });
            frame.add(btnDisc);

            JButton btnCursos = new JButton("Gerenciar Cursos");
            btnCursos.addActionListener(e -> {
                try { new CursoForm(frame).setVisible(true); } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(frame, ex.getMessage()); }
            });
            frame.add(btnCursos);

            JButton btnProf = new JButton("Gerenciar Professores");
            btnProf.addActionListener(e -> {
                try { new ProfessorForm(frame).setVisible(true); } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(frame, ex.getMessage()); }
            });
            frame.add(btnProf);

            JButton btnIns = new JButton("Gerenciar Inscrições");
            btnIns.addActionListener(e -> {
                try { new InscricaoForm(frame).setVisible(true); } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(frame, ex.getMessage()); }
            });
            frame.add(btnIns);

            JButton btnInscritos = new JButton("Consultar Inscritos por Disciplina");
            btnInscritos.addActionListener(e -> {
                try { new InscritosPorDisciplinaForm(frame).setVisible(true); } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(frame, ex.getMessage()); }
            });
            frame.add(btnInscritos);

            JButton btnDispProc = new JButton("Disciplinas com Processos Abertos");
           btnDispProc.addActionListener(e -> {
             try { new DisciplinasComProcessosForm(frame).setVisible(true); } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(frame, ex.getMessage()); }
            });
            frame.add(btnDispProc);

            frame.setLocationRelativeTo(null);
           frame.setVisible(true);
        });
    }
}
