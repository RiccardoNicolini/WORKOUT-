package org.example.workout2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.workout2.*; // Assicurati che il package sia corretto

public class WorkoutApp extends Application {

    private Stage window;
    private Scene loginScene, planScene;
    private WorkoutPlan plan;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Workout Tracker Pro");
        creaSchermataLogin();
        window.setScene(loginScene);
        window.show();
    }

    private void creaSchermataLogin() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        ComboBox<Integer> freqCombo = new ComboBox<>();
        freqCombo.getItems().addAll(1, 2, 3, 4);
        freqCombo.setValue(3);

        Label titolo = new Label("Configurazione Utente");
        titolo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Selezione Sesso con logica "Altro"
        ComboBox<String> sessoCombo = new ComboBox<>();
        sessoCombo.getItems().addAll("Maschio", "Femmina", "Altro");
        sessoCombo.setValue("Maschio");

        TextField pesoInput = new TextField();
        pesoInput.setPromptText("Peso (kg)");

        TextField altezzaInput = new TextField();
        altezzaInput.setPromptText("Altezza (cm)");

        ComboBox<TrainingLevel> livelloCombo = new ComboBox<>();
        livelloCombo.getItems().addAll(TrainingLevel.values());
        livelloCombo.setValue(TrainingLevel.MEDIUM);

        Button generaBtn = new Button("Genera Piano");
        generaBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");


        generaBtn.setOnAction(e -> {
            try {
                double peso = Double.parseDouble(pesoInput.getText());
                double altezza = Double.parseDouble(altezzaInput.getText());

                // Logica richiesta: Altro = 50% probabilità Maschio o Femmina
                String sessoScelto = sessoCombo.getValue();
                if (sessoScelto.equals("Altro")) {
                    sessoScelto = Math.random() < 0.5 ? "Maschio" : "Femmina";
                }

                // Creazione utente
                User user = new User(sessoScelto, peso, altezza, livelloCombo.getValue(), freqCombo.getValue());
                generaPianoWorkout(user);
                creaSchermataWorkout();
                window.setScene(planScene);

            } catch (NumberFormatException ex) {
                mostraAlert("Errore", "Inserisci numeri validi per peso e altezza.");
            }


        });




        layout.getChildren().addAll(
                titolo,
                new Label("Sesso:"), sessoCombo,
                new Label("Frequenza (giorni):"), freqCombo,
                new Label("Peso (kg):"), pesoInput, new Label("Altezza (cm):"), altezzaInput,
                new Label("Livello:"), livelloCombo, generaBtn

        );


        loginScene = new Scene(layout, 350, 480);
    }

    private void addLegsToWorkout(Workout w, User u, WeightCalculationStrategy s) {
        w.addExercise(new FundamentalExercise("Squat", 1.2));
        w.getExercises().get(w.getExercises().size()-1).generateParameters(u, s);
    }

    private void generaPianoWorkout(User user) {
        plan = new WorkoutPlan();
        WeightCalculationStrategy strategy = switch (user.getLevel()) {
            case LOW -> new BeginnerStrategy();
            case HIGH -> new AdvancedStrategy();
            default -> new IntermediateStrategy();
        };

        // Generazione allenamenti tramite Factory
        int freq = user.getWeeklyFrequency();
        if (freq == 1) {
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.FULL_BODY, user, strategy));
        } else if (freq == 2) {
            // Tirata-Gambe e Spinta-Gambe
            Workout d1 = WorkoutFactory.createWorkout(WorkoutType.PULL, user, strategy);
            addLegsToWorkout(d1, user, strategy);
            plan.addWorkout(d1);

            Workout d2 = WorkoutFactory.createWorkout(WorkoutType.PUSH, user, strategy);
            addLegsToWorkout(d2, user, strategy);
            plan.addWorkout(d2);
        } else if (freq == 3) {
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.PULL, user, strategy));
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.LEGS, user, strategy));
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.PUSH, user, strategy));
        } else { // 4 o più
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.PULL, user, strategy));
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.LEGS, user, strategy));
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.PUSH, user, strategy));
            plan.addWorkout(WorkoutFactory.createWorkout(WorkoutType.CARDIO, user, strategy));
        }
    }

    private void creaSchermataWorkout() {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));

        Label titolo = new Label("Il tuo Piano Settimanale");
        titolo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(titolo);

        VBox listContainer = new VBox(10);
        // Ciclo sui workout del piano
        for (Workout w : plan.getWeeklyWorkouts()) {
            VBox card = new VBox(5);
            card.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-padding: 10; -fx-background-color: #f9f9f9;");

            Label nome = new Label(w.getType().toString());
            nome.setStyle("-fx-font-weight: bold;");
            card.getChildren().add(nome);

            for (Exercise ex : w.getExercises()) {
                // Mostra dettagli esercizio
                card.getChildren().add(new Label(String.format("• %s: %d x %d @ : %.1f kg",
                        ex.name, ex.sets, ex.reps, ex.weight)));
            }

            // Checkbox di completamento
            CheckBox cb = new CheckBox("Completato");
            cb.setSelected(w.isCompleted());
            cb.setOnAction(e -> w.markAsCompleted(cb.isSelected()));

            card.getChildren().add(cb);
            listContainer.getChildren().add(card);
        }

        ScrollPane scroll = new ScrollPane(listContainer);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(400);

        Button valutaBtn = new Button("Concludi Settimana");
        valutaBtn.setMaxWidth(Double.MAX_VALUE);
        valutaBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        valutaBtn.setOnAction(e -> {
            // Verifica se tutti i workout sono stati completati
            boolean tuttiCompletati = plan.getWeeklyWorkouts().stream().allMatch(Workout::isCompleted);

            plan.evaluateProgression();

            if (tuttiCompletati) {
                mostraAlert("Progressione!", "Tutti completati! Intensità aumentata del 5%.");
            } else {
                mostraAlert("Invariato", "Workout mancanti. L'intensità resta uguale.");
            }

            // Ricarica la grafica per mostrare i nuovi pesi
            creaSchermataWorkout();
            window.setScene(planScene);
        });

        mainLayout.getChildren().addAll(scroll, valutaBtn);
        planScene = new Scene(mainLayout, 450, 600);
    }

    private void mostraAlert(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}