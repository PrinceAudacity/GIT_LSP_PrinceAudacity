// --------------------------------------------------------------------------
//
// NAME                    : LavaLayout.java
// VERSION                 : 
// COMPILED USING          : SUN JDK 1.1.2 / KAWA IDE 2.5
// AUTHOR                  : Dan Page (dan@hnet.demon.co.uk)
// LAST REVISION DATE      : 
// NOTES                   : 
//
// --------------------------------------------------------------------------

// --------------------------------------------------------------------------
// Import all the super duper java class files.
// --------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import java.util.*;
 
// --------------------------------------------------------------------------
// Define the main class.
// --------------------------------------------------------------------------
public class LavaLayout implements LayoutManager 
{

	// --------------------------------
	// Define private member variables.
	// --------------------------------
	private Dimension gridSize   = null;
	private Dimension gapSize    = null;
	private Hashtable components = null;
	
	private int PREFERRED        = 0;
	private int MINIMUM          = 1;
	private int MAXIMUM          = 2;

  // -----------------------------------------
  // Define a constructor to set up the class.
  // -----------------------------------------
	public LavaLayout( int dx, int dy ) { this( dx, dy, 0, 0 ); }

  // -----------------------------------------
  // Define a constructor to set up the class.
  // -----------------------------------------
	public LavaLayout( int dx, int dy, int xGap, int yGap ) 
	{
  
  	// Create member variables.
  	gridSize   = new Dimension( dx, dy );
  	gapSize    = new Dimension( xGap, yGap );
  	components = new Hashtable();
  	
	}

	// ------------------------------------------------------------------
	// Define a method to add a layout component to the layout manager.
	// We need not worry about this due to the addition of constraints in
	// a different method.
	// ------------------------------------------------------------------
	public void addLayoutComponent( String name, Component component ) 
	{
	
	}
	
	// --------------------------------------------------------------
	// Define a method to remove a component from the layout manager.
	// --------------------------------------------------------------
	public void removeLayoutComponent( Component component ) 
	{
  	
  	// Remove the component from the components hashtable.
  	components.remove( component );
  	
	}
	
	// ----------------------------------------------------------------
	// Define a method to set the constraints of a component.  This is
	// the same as in the GridBag layout and provides extra information
	// about the component layout to the layout manager.
	// ----------------------------------------------------------------
	public void setConstraints( Component component, LavaLayoutConstraints constraints ) 
	{
	
		// Add the component/constraints pair to the components hastable.
    try { components.put( component, constraints.clone() ); }
    catch( CloneNotSupportedException e ) { }
  
	}

	// -----------------------------------------------------------------------------
	// Define a method to retrieve a constraints object for the specified component.
	// -----------------------------------------------------------------------------
	public LavaLayoutConstraints getConstraints( Component component ) 
	{
  
  	// Try and get the contraints using the component as a key.
  	LavaLayoutConstraints constraints = ( LavaLayoutConstraints )( components.get( component ) );
  	
  	// Check if we got the constraints or not.
  	if( constraints != null ) 
  	{
  	
  		// Try and clone the constraints so we can return them to the caller.
  		try { return ( LavaLayoutConstraints )( constraints.clone() ); }
  		catch( CloneNotSupportedException e ) { return null; }
  		
  	}
  	else { return null; }
  	
	}

	// ------------------------------------------------------
	// Define a method to get the minimum size of the layout.
	// ------------------------------------------------------
	public Dimension minimumLayoutSize( Container parent ) { return calcLayoutSize( parent, MINIMUM ); }

	// --------------------------------------------------------
	// Define a method to get the preferred size of the layout.
	// --------------------------------------------------------
	public Dimension preferredLayoutSize( Container parent ) { return calcLayoutSize( parent, PREFERRED ); }

	// --------------------------------------------------------
	// Define a method to implement an algorithm to generically 
	// calculate layout sizes based on type.
	// --------------------------------------------------------
	private Dimension calcLayoutSize( Container parent, int sizeType ) 
	{
	
		// Define local variables.
  	Component component                	= null;
  	Dimension componentSize            	= null;
   	LavaLayoutConstraints componentCons 	= null;
  	Dimension preferredSize            	= null;
  	double maximumX                    	= 0;
  	double maximumY                    	= 0;
  	double tempX                       	= 0;
  	double tempY                       	= 0;
   	int counter                        	= 0;

  	// Loop through all the components and generate a maximum x and y size for container.
  	for ( counter = 0; counter < parent.getComponentCount(); counter++ ) 
  	{
    
    	// Get the current component and its and constraint object.
    	component     = parent.getComponent( counter );
    	componentCons = ( LavaLayoutConstraints )( components.get( component ) );
    
    	// Get the size of the component.
     	if     ( sizeType == PREFERRED ) { componentSize = component.getPreferredSize(); }
    	else if( sizeType == MINIMUM )   { componentSize = component.getMinimumSize(); }
    	else if( sizeType == MAXIMUM )   { componentSize = component.getMaximumSize(); }
    	else                             { componentSize = null; }
    
    	// Check if the constraints are null.
    	if( componentCons != null && componentSize != null ) 
    	{
    	
    		// Calculate temp x and y values.
    		tempX = ( double )( componentSize.width ) / ( double )( componentCons.gridWidth );
    		tempY = ( double )( componentSize.height ) / ( double )( componentCons.gridHeight );
    	
    		// Check if we need to update current maximum x and y values.
    		if ( tempX > maximumX ) { maximumX = tempX; }
    		if ( tempY > maximumY ) { maximumY = tempY; }
    	
    	}
  	
  	}
  	
  	// Generate the final preferred size.
  	preferredSize        = new Dimension( 0, 0 );
  	preferredSize.width  = parent.getInsets().left + parent.getInsets().right + ( int )( gridSize.width * maximumX ) + ( ( gridSize.width + 1 ) * gapSize.width );
  	preferredSize.height = parent.getInsets().top + parent.getInsets().bottom + ( int )( gridSize.height * maximumY ) + ( ( gridSize.height + 1 ) * gapSize.height );
  	
  	// Return the new dimension to caller.
  	return preferredSize;
		       
	}

	// ---------------------------------------------------------------------------
	// Define the all mighty layout method that lays out all the components using
	// their constraints as layout information.
	// ---------------------------------------------------------------------------
	public void layoutContainer( Container parent ) 
	{
	
		// Define local varaibles.
  	Component component                	= null;
  	LavaLayoutConstraints componentCons 	= null;
  	Dimension componentSize            	= null;
  	Point componentLocation            	= null;
   	Dimension gridDimension            	= null;
  	int counter                        	= 0;
  		
  	// Calculate the dimension of each element of the grid.
  	gridDimension        = new Dimension( 0, 0 );
  	gridDimension.width  = ( int )( ( double )( parent.getSize().width - parent.getInsets().left - parent.getInsets().right ) / ( double )( gridSize.width ) );
  	gridDimension.height = ( int )( ( double )( parent.getSize().height - parent.getInsets().top - parent.getInsets().bottom ) / ( double )( gridSize.height ) );
  	
  	// Loop through all the components and lay them all out.
  	for( counter = 0; counter < parent.getComponentCount(); counter++ ) 
  	{
    
    	// Get the current component and its and constraint object.
    	component     = parent.getComponent( counter );
    	componentCons = ( LavaLayoutConstraints )( components.get( component ) );
    	
    	// Check the constraints object is not null.
    	if( componentCons != null )
    	{
    	
    		// Calculate component size.
    		componentSize        = new Dimension( 0, 0 );
    		componentSize.width  = ( int )( gridDimension.width * componentCons.gridWidth - 2 * gapSize.width );
    		componentSize.height = ( int )( gridDimension.height * componentCons.gridHeight - 2 * gapSize.height );
    		
    		// Calculate component location.
    		componentLocation   = new Point( 0, 0 );
    		componentLocation.x = ( int )( gridDimension.width * componentCons.gridX + gapSize.width + parent.getInsets().left );
    		componentLocation.y = ( int )( gridDimension.height * componentCons.gridY + gapSize.height + parent.getInsets().top );
    	
    		// Set the component location and size.
    		component.setSize( componentSize );
    		component.setLocation( componentLocation );
	      
	    }
	    
  	}
  
	}

}