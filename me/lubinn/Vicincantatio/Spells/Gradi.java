package me.lubinn.Vicincantatio.Spells;

class Gradi extends ConstructionSpell {
	public Gradi() {
		this.area = new Platform();
		this.rangeBase = 15.0D;
		this.rangeGrowth = 7.5D;
		this.alwaysFlight = true;
	}
}