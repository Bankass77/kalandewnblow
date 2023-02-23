/*
 * package com.kalanblowSystemManagement.Kalanblow.config;
 * 
 * import org.optaplanner.core.config.constructionheuristic.
 * ConstructionHeuristicPhaseConfig; import
 * org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicType;
 * import org.optaplanner.core.config.phase.PhaseConfig; import
 * org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig; import
 * org.optaplanner.core.config.solver.SolverConfig;
 * 
 * import com.kalanblowSystemManagement.Kalanblow.domain.Course; import
 * com.kalanblowSystemManagement.Kalanblow.domain.KalanblowTimetable;
 * 
 * public class DefaultSolverConfiguration extends SolverConfig {
 * 
 * public DefaultSolverConfiguration() {
 * 
 * super();
 * 
 * @SuppressWarnings("removal") ScoreDirectorFactoryConfig
 * scoreDirectorFactoryConfig = new
 * ScoreDirectorFactoryConfig().withScoreDrls("default.drl");
 * 
 * PhaseConfig phaseConfig = new
 * ConstructionHeuristicPhaseConfig().withConstructionHeuristicType(
 * ConstructionHeuristicType.FIRST_FIT);
 * 
 * this.withSolutionClass(KalanblowTimetable.class).withEntityClasses(Course.
 * class).withPhases(phaseConfig)
 * .withScoreDirectorFactory(scoreDirectorFactoryConfig) .withDaemon(false);
 * 
 * } }
 */