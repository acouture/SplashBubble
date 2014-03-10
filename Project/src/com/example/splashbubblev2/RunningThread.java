package com.example.splashbubblev2;

public class RunningThread extends Thread {

	static final long FPS = 20;
	private GameController gameController;
	private boolean running = false;

	public RunningThread(GameController gameController) {
		this.gameController = gameController;
	}

	public void setRunning(boolean run) {
		running = run;
	}
	
	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {
		super.run();

		long ticksPerSecond = 1000 / FPS;
		long startTime;
		long sleepTime;

		long nextSpawnTime = System.currentTimeMillis() + 1000;

		while (running) {
			startTime = System.currentTimeMillis();

			if (startTime > nextSpawnTime) {
				gameController.getGameView().addBubble(EBubbleType.BLUE);
			}

			sleepTime = ticksPerSecond
					- (System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {
			}
		}
	}
}
