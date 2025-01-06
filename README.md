# Proyecto 2: Pelea de Sagas

Este proyecto simula una serie de combates entre personajes de **Star Wars** y **Star Trek**, utilizando colas de prioridad para gestionar el ciclo de vida de los procesos. La simulación recrea batallas para determinar un ganador definitivo entre ambas franquicias.

---

## Objetivo

El objetivo principal es implementar una simulación que:

1. Gestione personajes de **Star Wars** y **Star Trek** en colas de prioridad según su calidad.
2. Utilice una Inteligencia Artificial para decidir el resultado de las batallas.
3. Administre los ciclos de combate y actualice las colas dinámicamente.
4. Ofrezca una experiencia interactiva a través de una interfaz gráfica.

---

## Funcionalidades Principales

1. **Creación de Personajes**:
   - Generar 20 personajes para cada franquicia al inicio de la simulación.
   - Clasificación automática de personajes en colas de prioridad (1, 2, 3) y una cola de refuerzo.

2. **Simulación de Combates**:
   - Procesamiento de batallas por la Inteligencia Artificial (IA).
   - Posibles resultados de cada combate:
     - **Ganador**: El personaje perdedor se elimina y el ganador se agrega a una lista.
     - **Empate**: Ambos personajes regresan a la última posición de sus respectivas colas de nivel 1.
     - **Combate no realizado**: Los personajes son enviados a la cola de refuerzo.

3. **Gestión de Colas**:
   - Actualización dinámica de las colas después de cada combate.
   - Incremento del nivel de prioridad de los personajes inactivos tras 8 ciclos.

4. **Interfaz Gráfica**:
   - Visualización en tiempo real de las colas, personajes en combate y resultados.
   - Control de la velocidad de la simulación.
   - Marcador de combates ganados por cada franquicia.

5. **Adición Dinámica de Personajes**:
   - Nuevos personajes se agregan a las colas cada dos ciclos con un 80% de probabilidad.

---

## Estructura del Proyecto

- **Administrador**:
  - Gestiona las colas y organiza los combates.
  - Añade nuevos personajes y actualiza el estado de la simulación.

- **Inteligencia Artificial (IA)**:
  - Determina el resultado de los combates.
  - Calcula probabilidades basadas en estadísticas de los personajes.

- **Personajes**:
  - Cada personaje cuenta con un ID único y atributos (habilidades, puntos de vida, fuerza, agilidad).

- **Colas de Prioridad**:
  - Cuatro colas por franquicia: nivel 1, nivel 2, nivel 3 y refuerzo.
