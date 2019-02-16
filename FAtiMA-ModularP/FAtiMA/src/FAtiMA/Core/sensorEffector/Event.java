/** 
 * Event.java - Represents an external event that happened in the virtual world
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
 * Created: 17/01/2004 
 * @author: Jo�o Dias
 * Email to: joao.assis@tagus.ist.utl.pt
 * 
 * History: 
 * Jo�o Dias: 17/01/2004 - File created
 * Jo�o Dias: 23/05/2006 - Added comments to each public method's header
 * Jo�o Dias: 02/07/2006 - Replaced System's timer by an internal agent simulation timer
 * Jo�o Dias: 10/07/2006 - the class is now serializable 
 * Jo�o Dias: 18/07/2006 - Added the equals(Object) method
 * Jo�o Dias: 21/07/2006 - Added the clone method
 * Jo�o Dias: 25/07/2006 - Changed the implementation of the toString method,
 * 						   now it doesn't print the name of the parameters
 * Jo�o Dias: 18/09/2006 - References to the SELF are now made by the string "[SELF]" instead
 * 						   of the previous string "SELF"
 * 						 - Changes in methods ParseEvent and MatchEvent so that we can 
 * 						   use unspecified parameters (represented by "*") and a "[SELF]" 
 * 					       parameter in ReactionRules
 * 						 - Created the GenerateBindings method
 * Jo�o Dias: 29/09/2006 - Added method toStepName
 * Jo�o Dias: 03/10/2006 - Added method MakeGround(ArrayList) that works similarly to
 * 						   method MakeGround of GroundableNames
 * Joao Dias: 08/10/2006 - Solved a very stupid bug in the clone method that was causing the 
 * 						   parameters not to be cloned
 * Jo�o Dias: 14/12/1006 - Solved another bug. I was forgetting to replace the "*" in parsed
 * 						   event's action for a null action.
 * 						   
 */

package FAtiMA.Core.sensorEffector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;


import FAtiMA.Core.AgentSimulationTime;
import FAtiMA.Core.memory.episodicMemory.Time;
import FAtiMA.Core.util.Constants;
import FAtiMA.Core.wellFormedNames.Name;
import FAtiMA.Core.wellFormedNames.Substitution;
import FAtiMA.Core.wellFormedNames.Symbol;


/**
 * Represents an external event that happened in the virtual world
 * 
 * @author Jo�o Dias
 */
public class Event implements Cloneable, Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String _action;
	protected ArrayList<Parameter> _parameters;
	protected String _subject;
	protected String _target;
	protected long _time;
	
	// 06/01/10 - Meiyii
	protected short _status;
	protected short _type;
	
	/**
	 * Receives two events and sees if they match. Two events match if they
	 * refer to the same subject, action, target and parameters
	 * @param matchRule - left Event to be matched
	 * @param eventPerception - right Event to be matched
	 * @return true if the events match, false otherwise
	 */
	public static boolean MatchEvent(Event matchRule, Event eventPerception) {
		if (matchRule._subject != null && eventPerception._subject != null) {
			
			if (!matchRule._subject.equals(eventPerception._subject)) 
				return false;
		}
		
		if (matchRule._action != null && !matchRule._action.equals(eventPerception._action)) {
		    return false;
		}
		
		if (matchRule._target != null) {
			if (!matchRule._target.equals(eventPerception._target)) 
				return false;
		}
		
		if(matchRule._parameters != null) {
			Parameter p1;
			Parameter p2;
			ListIterator<Parameter> li1 = matchRule._parameters.listIterator();
			ListIterator<Parameter> li2 = eventPerception._parameters.listIterator();
			while(li1.hasNext()) {
				if(!li2.hasNext()) return false;
				p1 =  li1.next();
				p2 =  li2.next();
				if(!p1.GetValue().equals("*") && !p1.GetValue().equals(p2.GetValue()))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public Event ApplyPerspective(String agentName)
	{
		Parameter p1;
		Parameter p2;
		
		Event e = new Event(this._subject);
		
		
		if(this._subject != null && this._subject.equals(agentName))
		{
			e._subject = Constants.SELF;
		}
		else
		{
			e._subject = this._subject;
		}
		
		e._action = this._action;
		
		if(this._target != null && this._target.equals(agentName))
		{
			e._target = Constants.SELF;
		}
		else
		{
			e._target = this._target;
		}
		
		e._type = this._type;
		e._status = this._status;
		e._time = this._time;
		
		if(this._parameters != null)
		{
			e._parameters = new ArrayList<Parameter>();
			ListIterator<Parameter> li = this._parameters.listIterator();
			while(li.hasNext())
			{
				p1 = li.next();
				if(p1.GetValue().equals(agentName))
				{
					p2 = new Parameter("param",Constants.SELF);
				}
				else
				{
					p2 = (Parameter) p1.clone();
				}
				e._parameters.add(p2);
			}
		}
		
		return e;
	}
	
	public Event RemovePerspective(String agentName)
	{
		Parameter p1;
		Parameter p2;
		
		Event e = new Event(this._subject);
		
		if(this._subject.equals(Constants.SELF))
		{
			e._subject = agentName;
		}
		else
		{
			e._subject = this._subject;
		}
		
		e._action = this._action;
		
		if(this._target != null && this._target.equals(Constants.SELF))
		{
			e._target = agentName;
		}
		else
		{
			e._target = this._target;
		}
		
		e._time = this._time;
		e._type = this._type;
		e._status = this._status;
		
		if(this._parameters != null)
		{
			e._parameters = new ArrayList<Parameter>();
			ListIterator<Parameter> li = this._parameters.listIterator();
			while(li.hasNext())
			{
				p1 = li.next();
				if(p1.GetValue().equals(Constants.SELF))
				{
					p2 = new Parameter("param",agentName);
				}
				else
				{
					p2 = (Parameter) p1.clone();
				}
				e._parameters.add(p2);
			}
		}
		
		return e;
	}
	
	
	/**
	 * Parses an event from a XML attributes list
	 * @param selfName - the name of the agent (thus its called selfName)
	 * @param attributes - the XML attributes list
	 * @return the parsed Event
	 */
	public static Event ParseEvent(String subject, String action, String target, String parameters){
		String aux;
		Event e;
		

		if(subject != null) {
			
			if(subject.equals("OTHER") || subject.equals("ANY") || subject.equals("*")) {
				subject = null;
			}
		}
		
		if(action != null)
		{
			if(action.equals("ANY") || action.equals("*"))
			{
				action = null;
			}
		}
		
		if(target != null) {
			if(target.equals("OTHER") || target.equals("ANY") || target.equals("*")) {
				target = null;
			}
		}
		
		e = new Event(subject,action,target);

		if(parameters != null) {
			StringTokenizer st = new StringTokenizer(parameters, ",");
			while(st.hasMoreTokens()) {
				aux = st.nextToken();
				if(aux.equals("OTHER") || aux.equals("ANY") || aux.equals("*"))
				{
					e.AddParameter(new Parameter("param","*"));
				}
				else
				{
					e.AddParameter(new Parameter("param",aux));
				}
			}
		}
		return e;
	}
	
	/**
	 * Creates a new Event
	 * @param subject - the subject of the event (who performed the action)
	 */
	public Event(String subject) {
		_parameters = new ArrayList<Parameter>();
		_time = AgentSimulationTime.GetInstance().Time();
		_subject = subject;
	}
	
	/**
	 * Creates a new Event
	 * @param subject - the subject of the event (who performed the action)
	 * @param action - the action specified by the event
	 * @param target - the target of the action
	 */
	public Event(String subject, String action, String target) {
		_time = AgentSimulationTime.GetInstance().Time();
		_parameters = new ArrayList<Parameter>();
		_subject = subject;
		_action = action;
		_target = target;
	}

	/**
	 * Creates a new event
	 * @author Mei Yii Lim
	 * @param subject - the subject of the event (who performed the action)
	 * @param action - the action specified by the event
	 * @param target - the target of the action
	 * @param type - the type of event
	 * @param status - the status of the goal/action
	 */
	public Event(String subject, String action, String target, short type, short status) {
		_time = AgentSimulationTime.GetInstance().Time();
		_parameters = new ArrayList<Parameter>();
		_subject = subject;
		_action = action;
		_target = target;
		_type = type;
		_status = status;
	}
	
	public Event(String subject, String action, String target, short type, short status, Long time) {
		_parameters = new ArrayList<Parameter>();
		_subject = subject;
		_action = action;
		_target = target;
		_type = type;
		_status = status;
		_time = time;
	}
	
	/**
	 * Adds a Parameter to the event (usually arguments or parameters of the action)
	 * @param param - the Parameter to add
	 * @see Parameter
	 */
	public void AddParameter(Parameter param) {
		_parameters.add(param);
	}

	/**
	 * Gets the event's action
	 * @return the event's action
	 */
	public String GetAction() {
		return _action;
	}
	
	/**
	 * Gets the event's parameters (usually arguments or parameters of the action)
	 * @return an ArrayList with all the parameters
	 * @see Parameter
	 */
	public ArrayList<Parameter> GetParameters() {
		return _parameters;
	}

	/**
	 * Gets the event's subject (Who performed the action)
	 * @return the event's subject
	 */
	public String GetSubject() {
		return _subject;
	}

	/**
	 * Meiyii 07/01/10
	 * Gets the event's target (what is the target of the event's action)
	 * @return the event's target
	 */
	public String GetTarget() {
		return _target;
	}
	
	/**
	 * Meiyii 07/01/10
	 * Gets the event's type (whether goal or action)
	 * @return the event's type
	 */
	public short GetType() {
		return _type;
	}

	/**
	 * Gets the event's status 
	 * (activation, success, failure for goal and success, failure for action)
	 * @return the event's status
	 */
	public short GetStatus() {
		return _status;
	}
	
	/**
	 * Gets the event's time
	 * @return the event's time
	 */
	public long GetTime() {
		return _time;
	}
	
	/**
	 * Sets the event's action (what happened)
	 * @param action - the action to store in the event
	 */
	public void SetAction(String action) {
		_action = action;
	}

	/**
	 * Sets the event's subject (who performed the action)
	 * @param subject - the subject to store in the event
	 */
	public void SetSubject(String subject) {
		_subject = subject;
	}

	/**
	 * Meiyii 07/01/10
	 * Sets the event's status 
	 * (activation, success, failure for goal and success, failure for action)
	 * @param status - the status of the event
	 */
	public void SetStatus(short status) {
		_status = status;
	}
	
	/**
	 * Meiyii 07/01/10
	 * Sets the event's type (goal or action)
	 * @param type - the type of the event
	 */
	public void SetType(short type) {
		_type = type;
	}
	
	/**
	 * Sets the event's target (what is the target of the event's action)
	 * @param target - the target to store in the event
	 */
	public void SetTarget(String target) {
		_target = target;
	}
	
	/**
	 * Compares the event against a given object
	 * @return true if the received object equals the event,
	 * 	       false otherwise
	 */
	public boolean equals(Object obj)
	{
		return this.toString().equals(obj.toString());
	}
	
	/**
	 * Creates a new copy of the Event
	 * @return a new Event equal to the cloned Event
	 */
	public Object clone()
	{
		Event e = new Event(this._subject);
		e._action = this._action;
		e._target = this._target;
		e._time = this._time;
		
		if(this._parameters != null)
		{
			e._parameters = new ArrayList<Parameter>();
			ListIterator<Parameter> li = this._parameters.listIterator();
			while(li.hasNext())
			{
				e._parameters.add((Parameter)li.next().clone());
			}
		}
		
		return e;
	}
	
	/**
	 * Applies a set of substitutions to the object, grounding it.
	 * Only considers substitutions that start with [Subject],[Target],
	 * [Action],[P1],[P2], etc...
	 * Example: Applying the substitution "[Subject]/John" in the event,
	 * will replace the events subject with John
	 *  
	 * Attention, this method modifies the original object.
	 * @param bindings - A list of substitutions of the type "[Variable]/value"
	 * @see Substitution
	 */
	public void MakeGround(ArrayList<Substitution> substitutions)
	{
		Substitution sub;
		String aux;
		
		for(ListIterator<Substitution> li = substitutions.listIterator();li.hasNext();)
		{
			sub = (Substitution) li.next();
			aux = sub.getVariable().toString();
			if(aux.equals("[Subject]"))
			{
				_subject = sub.getValue().toString();
			}
			else if(aux.equals("[Action]"))
			{
				_action = sub.getValue().toString();
			}
			else if(aux.equals("[Target]"))
			{
				_target = sub.getValue().toString();
			}
			else if(aux.equals("[P1]"))
			{
				GroundParams(sub.getValue().toString(),0);
			}
			else if(aux.equals("[P2]"))
			{
				GroundParams(sub.getValue().toString(),1);
			}
			else if(aux.equals("[P3]"))
			{
				GroundParams(sub.getValue().toString(),2);
			}
		}
	}
	
	private void GroundParams(String value, int index)
	{
		for(int i = 0; i <= index; i++)
		{
			if(i >= _parameters.size())
			{
				_parameters.add(new Parameter("param","*"));
			}
			
			if(i == index)
			{
				_parameters.set(i,new Parameter("param",value));
				return;
			}
		}
	}
	
	/**
	 * Generates a List of bindings that associate the Variables [Subject],
	 * [Action],[Target],[P1],[P2],... respectively to the event's subject, action,
	 * target and parameters 
	 * @return the mentioned list of substitutions
	 */
	public ArrayList<Substitution> GenerateBindings()
	{
		ArrayList<Substitution> bindings = new ArrayList<Substitution>(5);
		
		bindings.add(new Substitution(new Symbol("[Subject]"), new Symbol(_subject)));
		bindings.add(new Substitution(new Symbol("[Action]"), new Symbol(_action)));
		if(_target != null) {
			bindings.add(new Substitution(new Symbol("[Target]"), new Symbol(_target)));
		}
		
		int i=1;
		Parameter p;
		for(ListIterator<Parameter> li = _parameters.listIterator(); li.hasNext();i++)
		{
			p =  li.next();
			bindings.add(new Substitution(new Symbol("[P" + i + "]"), new Symbol(p.GetValue().toString())));
		}
		
		return bindings;
	}
	
	/**
	 * Converts the event to a Name
	 * @return the converted Name
	 * @see Name
	 */
	public Name toName() {
		String aux;
		Parameter p;
		
		aux = "EVENT(" + _subject;
		if(_action != null) {
			aux = aux + "," + _action;
			if(_target!= null) {
				aux = aux + "," + _target;
			}
			if(_parameters!=null) {
				ListIterator<Parameter> li = _parameters.listIterator();
				while(li.hasNext()) {
					p =  li.next();
					aux = aux + "," + p.GetValue();
				}
			}
		}
		aux = aux + ")";
		
		return Name.ParseName(aux);
	}
	
	/**
	 * Converts the event into the equivalent of a step's name 
	 * @return a Name that corresponds to a Step's description
	 */
	public Name toStepName()
	{
		String aux;
		Parameter p;
		
		aux = this._action + "(";
		if(this._target!= null) {
			aux = aux + this._target;
		}
		
		if(this._parameters != null)	
		{
			for(ListIterator<Parameter> li = this._parameters.listIterator();li.hasNext();)
			{
				p = li.next();
				aux = aux + "," + p.GetValue();
			}
		}
		aux = aux + ")";
		
		return Name.ParseName(aux);
	}
	
	/**
	 * Converts the event to a String
	 * @return the converted String
	 */
	public String toString() {
	    Parameter p;
	    String aux = _subject + " " + _action + " " + _target;
	    ListIterator<Parameter> li = _parameters.listIterator();
	    while(li.hasNext()) {
	        p =  li.next();
	        aux = aux + " "  + p.GetValue();
	    }
	    return aux;
	}
}