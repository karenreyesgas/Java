/** 
 * Action.java - Represents a Reactive Action that can be used in the Action Tendencies
 *  
 * Copyright (C) 2006 GAIPS/INESC-ID 
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Company: GAIPS/INESC-ID
 * Project: FAtiMA
 * Created: 14/01/2004 
 * @author: Jo�o Dias
 * Email to: joao.assis@tagus.ist.utl.pt
 * 
 * History: 
 * Jo�o Dias: 14/01/2004 - File created
 * Jo�o Dias: 23/05/2006 - Added comments to each public method's header
 * Jo�o Dias: 10/07/2006 - the class is now serializable
 * Jo�o Dias: 12/07/2006 - Replaced the deprecated Ground methods for the new ones.
 * Jo�o Dias: 18/09/2006 - Improved EmotionCheck method by adding substitutions related
 * 						   to an event's parameters. If the event has parameters,
 * 						   the returned binding list will contain the substitutions
 * 						   [P1]/param1, [P2]/param2, etc..
 * Jo�o Dias: 03/10/2006 - The activation of ActionTendencies now properly takes into account
 * 						   the list of preconditions. Now, more than one specific action can be
 * 						   activated by a generic ActionTendency (one for each possible 
 * 						   substitution set). 
 * 						 - Removed private method EmotionCheck(ActiveEmotion) that was no longer being
 * 						   used
 * Jo�o Dias: 27/12/2006 - Added the getters getName and getElicitingEmotion
 * 						 - Added the methods ReinforceAction and SuppressAction 
 */

package FAtiMA.ReactiveComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import FAtiMA.Core.AgentModel;
import FAtiMA.Core.IIntegrityTester;
import FAtiMA.Core.IntegrityValidator;
import FAtiMA.Core.ValuedAction;
import FAtiMA.Core.conditions.Condition;
import FAtiMA.Core.emotionalState.ActiveEmotion;
import FAtiMA.Core.emotionalState.BaseEmotion;
import FAtiMA.Core.exceptions.UnknownSpeechActException;
import FAtiMA.Core.sensorEffector.Event;
import FAtiMA.Core.wellFormedNames.IGroundable;
import FAtiMA.Core.wellFormedNames.Name;
import FAtiMA.Core.wellFormedNames.Substitution;
import FAtiMA.Core.wellFormedNames.SubstitutionSet;


/**
 * Represents a Reactive Action Tendency
 * @author Jo�o Dias
 */
public class Action implements IIntegrityTester, Serializable, IGroundable, Cloneable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BaseEmotion _elicitingEmotion;
	private Name _name;
	private ArrayList<Condition> _preConditions;

	/**
	 * Creates a new Action (Action Tendency)
	 * @param name - the name of the action
	 */
	public Action(Name name) {
		_name = name;
		_preConditions = new ArrayList<Condition>(3);
	}
	
	/**
	 * Gets the Action's name
	 * @return - the Name of the ActionTendency
	 */
	public Name getName()
	{
		return this._name;
	}
	
	/**
	 * Get's the Action Tendency's eliciting emotion
	 * @return the BaseEmotion that may trigger the Action Tendency
	 */
	public BaseEmotion GetElicitingEmotion()
	{
		return _elicitingEmotion;
	}
	
	public ArrayList<Condition> GetPreconditions()
	{
		return this._preConditions;
	}

	/**
	 * Adds a precondition to the ActionTendency (that must be verified in order
	 * for the action to be executed)
	 * @param cond - the Condition to add as precondition
	 */
	public void AddPreCondition(Condition cond) {
		_preConditions.add(cond);
	}
	 
	/**
	 * Checks the integrity of the Action Tendency, it checks if the action references
	 * a SpeechAct that is not defined, in that case it throws an exception
	 */
	public void CheckIntegrity(IntegrityValidator val) throws UnknownSpeechActException {
	    val.CheckSpeechAction(_name);
	}

	/**
	 * Sets the emotion that elicits this action tendency. For example, the victim's
	 * action tendency to cry is elicited by a strong distress emotion
	 * @param emotion - the emotion that will elicit this action
	 */
	public void SetElicitingEmotion(BaseEmotion emotion) {
		_elicitingEmotion = emotion;
	}
	
	/**
	 * Reinforces the likelihood of an action tendency to be activated,
	 * by lowering the minimum emotional intensity necessary to activate the AT
	 * by a given value
	 * @param value - the ammount to lower the minimum intensity
	 */
	public void ReinforceAction(int value)
	{
		if(_elicitingEmotion != null)
		{
			float newPotential = _elicitingEmotion.GetPotential() - value;
			if(newPotential < 0)
			{
				newPotential = 0;
			}
			_elicitingEmotion = new BaseEmotion(_elicitingEmotion);
			_elicitingEmotion.setPotential(newPotential);

		}
	}
	
	/**
	 * Suppresses the likelihood of an action tendency to be activated, by
	 * increasing the minimum emotional intensity necessary to activate the AT
	 * by a given value 
	 * @param value - the ammount used to increase the minimum intensity
	 */
	public void SuppressAction(int value)
	{
		if(_elicitingEmotion != null)
		{
			float newPotential = _elicitingEmotion.GetPotential() + value;
			if(newPotential > 10)
			{
				newPotential = 10;
			}
			_elicitingEmotion = new BaseEmotion(_elicitingEmotion);
			_elicitingEmotion.setPotential(newPotential);
		}
	}
	
	
	public ValuedAction TriggerAction(AgentModel am, Iterator<ActiveEmotion> emotionsIterator) {
		ActiveEmotion em;
		float maxValue = 0;
		Name action;
		ValuedAction va = null;
		ArrayList<SubstitutionSet> substitutionSets;
		SubstitutionSet subSet;
		Event groundEvent;
		
		//first we need to test the action tendency preconditions
		if(_preConditions.size() > 0)
		{
			substitutionSets = Condition.CheckActivation(am, _preConditions);
		}
		else
		{
			substitutionSets = new ArrayList<SubstitutionSet>();
			substitutionSets.add(new SubstitutionSet());
		}
		 
		if(substitutionSets != null) {
			for(Iterator<ActiveEmotion> it = emotionsIterator; it.hasNext();)
			{
				em = (ActiveEmotion) it.next();
				if(em.getType().equals(_elicitingEmotion.getType()) &&
				   em.GetIntensity() >= _elicitingEmotion.GetPotential())
				{
					//if the emotion has passed these two first tests, we need to
					//check if applying any possible SubstitutionSet to the expected
					//event will match with the perceived emotion event
					for(ListIterator<SubstitutionSet> li = substitutionSets.listIterator();li.hasNext();)
					{
						subSet = (SubstitutionSet) li.next();
						groundEvent = (Event) _elicitingEmotion.GetCause().clone();
						groundEvent.MakeGround(subSet.GetSubstitutions());
						if(Event.MatchEvent(groundEvent,em.GetCause()))
						{
							subSet.AddSubstitutions(em.GetCause().GenerateBindings());
							action = (Name) _name.clone();
							action.MakeGround(subSet.GetSubstitutions());
							//the action is selected only if elicited by the most intense emotion
							//who's intensity is greater than the specified minimum intensity (for this particular action)
							if (em.GetIntensity() > maxValue && action.isGrounded()) {
								maxValue = em.GetIntensity();
								va = new ValuedAction(ReactiveComponent.NAME, action, em);
							}
						}
					}
				}		
			}
		}
		return va;
	}
	
	public String toString()
	{
		return "AT " + _name + "- PreConditions " + _preConditions + " Emotion: " + _elicitingEmotion;
		
	}

	@Override
	public void MakeGround(ArrayList<Substitution> bindings) {
		this._name.MakeGround(bindings);
		ListIterator<Condition> li = this._preConditions.listIterator();
		
		while(li.hasNext())
		{
			li.next().MakeGround(bindings);
		}
		
	}

	@Override
	public void MakeGround(Substitution subst) {
		this._name.MakeGround(subst);
		ListIterator<Condition> li = this._preConditions.listIterator();
		
		while(li.hasNext())
		{
			li.next().MakeGround(subst);
		}
	}

	@Override
	public void ReplaceUnboundVariables(int variableID) {
		this._name.ReplaceUnboundVariables(variableID);
		ListIterator<Condition> li = this._preConditions.listIterator();
		
		while(li.hasNext())
		{
			li.next().ReplaceUnboundVariables(variableID);
		}	
	}

	@Override
	public boolean isGrounded() {
		if(!this._name.isGrounded()) return false;
		ListIterator<Condition> li = this._preConditions.listIterator();
		
		while(li.hasNext())
		{
			if(!li.next().isGrounded()) return false;
		}
		
		return true;
	}
	
	public Object clone()
	{
		Action act = new Action((Name)this._name.clone());
		act._elicitingEmotion = this._elicitingEmotion;
		act._preConditions = new ArrayList<Condition>(this._preConditions.size());
		
		ListIterator<Condition> li = this._preConditions.listIterator();
		while (li.hasNext())
		{
			act._preConditions.add((Condition)li.next().clone());
		}
		
		return act;
	}
}