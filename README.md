# JMeta-MO - Framework de Optimización Multi-objetivo con Restricciones

**JMeta-MO** es un framework de código abierto desarrollado en Java para resolver Problemas de Optimización Multi-objetivo con Restricciones (POMRs) mediante algoritmos bio-inspirados. Integra dos metaheurísticas clásicas adaptadas al criterio de dominancia de Pareto: **PSO** (Optimización por Enjambre de Partículas) y **TS-MBFOA** (Algoritmo de Forrajeo Bacteriano Modificado de doble nado).

---

## 📌 Características principales

- Implementación completa en Java 17.
- Evaluación de funciones objetivo y restricciones mediante un parser algebraico.
- Adaptación multi-objetivo basada en dominancia de Pareto.
- Integración de 10 problemas reales del benchmark CEC 2021.
- Métricas de evaluación: Hipervolumen y Two Set Cover.
- Soporte para visualización del frente de Pareto.
- Modular y extensible.

---

## 📦 Problemas incluidos

Se integran **10 problemas de referencia** provenientes del benchmark propuesto por Kumar et al. (CEC 2021), enfocados en aplicaciones reales con características de optimización continua y dos objetivos. Esta selección permite facilitar la visualización del frente de Pareto y evaluar de manera efectiva la calidad de las soluciones generadas por el framework.

| Código  | Nombre del problema                       | Objetivos | Variables | Restricciones (≠) | Restricciones (=) |
|---------|-------------------------------------------|-----------|-----------|--------------------|--------------------|
| PMO1    | Cantilever Beam Design                    | 2         | 2         | 2                  | 0                  |
| PMO2    | Design of a Disc Brake                    | 2         | 4         | 4                  | 0                  |
| PMO3    | Front Rail Design                         | 2         | 3         | 3                  | 0                  |
| PMO4    | Vibrating Platform                        | 2         | 5         | 5                  | 0                  |
| PMO5    | Two Bar Truss Design                      | 2         | 3         | 3                  | 0                  |
| PMO6    | Welded Beam Design                        | 2         | 4         | 4                  | 0                  |
| PMO7    | Two Bar Plane Truss                       | 2         | 2         | 2                  | 0                  |
| PMO8    | Simply Supported I-Beam Design            | 2         | 4         | 1                  | 0                  |
| PMO9    | Multiple-disk Clutch Brake Design         | 2         | 5         | 8                  | 0                  |
| PMO10   | Hydro-static Thrust Bearing Design        | 2         | 4         | 7                  | 0                  |

---

## 📊 Métricas de evaluación

Las soluciones generadas por el framework son evaluadas con métricas de calidad ampliamente aceptadas en la literatura:

- **Hipervolumen (HV):** mide el volumen del espacio objetivo dominado por el frente obtenido.
- **Two Set Cover (TSC):** evalúa la cobertura entre dos frentes para comparar dominancia relativa.
- **Tasa de finalización:** 90% de los problemas generaron al menos una solución factible.
- **Tasa de factibilidad:** más del 20% en promedio para soluciones generadas.

---

## 🚀 Requisitos

- Java 17 o superior
- Sistema operativo compatible con JVM

---

Puedes importar el proyecto directamente en NetBeans.


## 👨‍🔬 Créditos

Este framework fue desarrollado como parte de una tesis doctoral enfocada en metaheurísticas bio-inspiradas para la resolución de POMRs.

* Autor: José Adrian García López
* Universidad: Universidad Juárez Autónoma de Tabasco
* Año: 2025
